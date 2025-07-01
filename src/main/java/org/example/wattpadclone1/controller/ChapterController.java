package org.example.wattpadclone1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.example.wattpadclone1.dto.ChapterMediaDTO;
import org.example.wattpadclone1.entity.*;
import org.example.wattpadclone1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ParagraphService paragraphService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private ChapterMediaService chapterMediaService;

    @GetMapping("firstChapter")
    public String getFirstChapterOfStory(@RequestParam String id, Model model){

        Chapter chapter = chapterService.getChapterById(Integer.parseInt(id));

        if(chapter==null){
            System.out.println("no chapters in this story");
            return "error-page";
        }

        List<Paragraph> paragraphList = paragraphService.getAllParagraphsOfSelectedChapter(chapter);

        List<Chapter> chapterList = chapterService.getChaptersListByStory(chapter.getStory());

        if(chapterList.getLast().getId()==chapter.getId()){
            model.addAttribute("endOfTheStory","true");
        }
        else{
            model.addAttribute("endOfTheStory","false");
        }

        model.addAttribute("allChapters",chapterList);
        model.addAttribute("chapter",chapter);
        model.addAttribute("paragraphs",paragraphList);

        return "chapters-page";
    }

    @GetMapping("chapterById")
    public String getChapterById(@RequestParam String id, Model model){

        Chapter chapter = chapterService.getChapterById(Integer.parseInt(id));

        if(chapter==null){
            System.out.println("no chapters in this story");
            return "error-page";
        }

        List<Paragraph> paragraphList = paragraphService.getAllParagraphsOfSelectedChapter(chapter);

        List<Chapter> chapterList = chapterService.getChaptersListByStory(chapter.getStory());

        if(chapterList.getLast().getId()==chapter.getId()){
            model.addAttribute("endOfTheStory","true");
        }
        else{
            model.addAttribute("endOfTheStory","false");
        }

        model.addAttribute("allChapters",chapterList);
        model.addAttribute("chapter",chapter);
        model.addAttribute("paragraphs",paragraphList);

        return "chapters-page";
    }

    @GetMapping("nextChapter")
    public String getNextChapter(@RequestParam String storyId, @RequestParam String chapterId, Model model){

        Story story = storyService.getStoryDetailsById(Integer.parseInt(storyId));

        if(story==null){
            System.out.println("story null at chapter controller line 69");
            return "error-page";
        }

        List<Chapter> chapterList = chapterService.getChaptersListByStory(story);
        Chapter nextChapter = null;
        for (int i = 0; i < chapterList.size(); i++) {
            if(chapterList.get(i).getId()==Integer.parseInt(chapterId)){
                nextChapter = chapterList.get(i+1);
                break;
            }
        }

        List<Paragraph> paragraphList = paragraphService.getAllParagraphsOfSelectedChapter(nextChapter);

        if(chapterList.getLast().getId()==nextChapter.getId()){
            model.addAttribute("endOfTheStory","true");
        }
        else{
            model.addAttribute("endOfTheStory","false");
        }

        model.addAttribute("allChapters",chapterList);
        model.addAttribute("chapter",nextChapter);
        model.addAttribute("paragraphs",paragraphList);

        return "chapters-page";
    }

    @GetMapping("lastReadChapter")
    public String getLastReadChapter(@RequestParam String libraryId, Model model){

        Library library = libraryService.getLibraryById(Integer.parseInt(libraryId));

        List<Chapter> chapterList = chapterService.getChaptersListByStory(library.getStory());

        int lastReadChapterNo = library.getLastReadPage();
        Chapter lastReadChapter = chapterList.get(lastReadChapterNo-1);

        List<Paragraph> paragraphList = paragraphService.getAllParagraphsOfSelectedChapter(lastReadChapter);

        if(chapterList.getLast().getId()==lastReadChapter.getId()){
            model.addAttribute("endOfTheStory","true");
        }
        else{
            model.addAttribute("endOfTheStory","false");
        }

        model.addAttribute("allChapters",chapterList);
        model.addAttribute("chapter",lastReadChapter);
        model.addAttribute("paragraphs",paragraphList);

        return "chapters-page";
    }

    @PostMapping("/add")
    public String handleForm(HttpServletRequest request,
                             @RequestParam Map<String, String> stringInputs,
                             @RequestParam Map<String, MultipartFile> fileInputs,Model model) throws JsonProcessingException {

        int imageCount = 0;
        String storyId = "";
        String chapterTitle = "";
        int sequenceNo = 1;

        Story story = null;
        Chapter savedChapter = null;
        List<ChapterMediaDTO> chapterMediaDTOList = new ArrayList<>();

        // Text inputs (textareas and YouTube links)
        for (Map.Entry<String, String> entry : stringInputs.entrySet()) {
            String fieldName = entry.getKey();  // e.g., paragraphText_0, youtubeLink_1
            String value = entry.getValue();    // actual text or link

            System.out.println("Field: " + fieldName + " = " + value);

            if(fieldName.equalsIgnoreCase("storyId")){
                storyId = value;
            }
            else if(fieldName.equalsIgnoreCase("chapterTitle")){
                chapterTitle = value;
            }
            else if(fieldName.equalsIgnoreCase("chapterContentJson")){
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(value);
                JsonNode paragraphs = root.get("paragraphs");

                for (JsonNode p : paragraphs) {
                    String type = p.get("type").asText();

                    if ("text".equals(type)) {
                        System.out.println("Text value: " + p.get("value").asText());
                        Paragraph paragraph = new Paragraph(savedChapter,p.get("value").asText(),sequenceNo);
                        Paragraph savedParagraph = paragraphService.addParagraphForAChapter(paragraph);
                        sequenceNo++;
                        ChapterMediaDTO chapterMediaDTO = new ChapterMediaDTO("PARAGRAPH",p.get("value").asText());
                        chapterMediaDTOList.add(chapterMediaDTO);
                        System.out.println("saved Paragraph: "+savedParagraph);
                    }

                    JsonNode media = p.get("media");
                    if (media != null && media.has("type") && "video".equals(media.get("type").asText())) {
                        String videoUrl = media.get("url").asText();
                        System.out.println("Video URL: " + videoUrl);
                        String[] videoUrl2 = videoUrl.split(".be/");
                        String actualUrl = "https://www.youtube.com/embed/"+videoUrl2[1];
                        System.out.println("actualUrl: "+actualUrl);
                        ChapterMedia chapterMedia = new ChapterMedia(actualUrl,sequenceNo,savedChapter);
                        ChapterMedia savedChapterMedia = chapterMediaService.addMediaForAChapter(chapterMedia);
                        sequenceNo++;
                        ChapterMediaDTO chapterMediaDTO = new ChapterMediaDTO("VIDEO",actualUrl);
                        chapterMediaDTOList.add(chapterMediaDTO);
                        System.out.println("saved media: "+chapterMedia);
                    }
                    if (media != null && media.has("type") && "image".equals(media.get("type").asText())) {
                        // File inputs (images)
                        imageCount++;
                        int no = 1;
                        for (Map.Entry<String, MultipartFile> fileEntry : fileInputs.entrySet()) {
                            String key = fileEntry.getKey(); // e.g., paragraphImage_0
                            MultipartFile file = fileEntry.getValue();

                            if (!file.isEmpty() && no==imageCount) {
                                System.out.println("Image field: " + key);
                                System.out.println("Original filename: " + file.getOriginalFilename());

                                try {
                                    String originalFileName = file.getOriginalFilename();
                                    System.out.println("original file name: "+originalFileName);
                                    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                                    System.out.println("extension: "+extension);

                                    String sanitizedTitle = key.replaceAll("[^a-zA-Z0-9]", "_");
                                    System.out.println("sani: "+sanitizedTitle);
                                    String uniqueFileName = System.currentTimeMillis() + "_" + sanitizedTitle + extension;
                                    System.out.println("unique: "+uniqueFileName);

                                    String uploadDir = "C:/wattpad-uploads";
                                    System.out.println("dir: "+uploadDir);
                                    File uploadPath = new File(uploadDir);
                                    if (!uploadPath.exists()) {
                                        uploadPath.mkdirs();
                                    }

                                    File destinationFile = new File(uploadPath, uniqueFileName);
                                    file.transferTo(destinationFile);

                                    ChapterMedia chapterMedia = new ChapterMedia(uniqueFileName,sequenceNo,savedChapter);
                                    ChapterMedia savedChapterMedia = chapterMediaService.addMediaForAChapter(chapterMedia);
                                    sequenceNo++;
                                    ChapterMediaDTO chapterMediaDTO = new ChapterMediaDTO("IMAGE",uniqueFileName);
                                    chapterMediaDTOList.add(chapterMediaDTO);
                                    System.out.println("saved media: "+chapterMedia);

                                } catch (Exception fileException) {
                                    System.err.println("Error processing cover image: " + fileException.getMessage());
                                    // Continue without the image rather than failing completely
                                }
                                break;
                            }
                            no++;
                        }
                    }

                    System.out.println("------");
                }
            }

            if(!storyId.isEmpty() && !chapterTitle.isEmpty()){
                story = storyService.getStoryDetailsById(Integer.parseInt(storyId));
                System.out.println("story: "+story);

                Chapter chapter = new Chapter();
                chapter.setTitle(chapterTitle);
                chapter.setStory(story);
                chapter.setViews("1");
                chapter.setVotes("0");
                chapter.setComments("0");
                chapter.setPublishDate(LocalDateTime.now());

                savedChapter = chapterService.saveNewChapterForStory(chapter);

                storyId = "";
                chapterTitle = "";
            }
        }

        model.addAttribute("story",story);
        model.addAttribute("chapter",savedChapter);
        model.addAttribute("chapterContent",chapterMediaDTOList);
        return "chapters-page-with-media";
    }
}
