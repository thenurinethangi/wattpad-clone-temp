package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.ChapterService;
import org.example.wattpadclone1.service.StoryService;
import org.example.wattpadclone1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping("createStory/{userId}")
    public String createStory(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        return "create-new-story-page";
    }

    @PostMapping("createNewStory")
    public String createStoryFirstStep(@RequestParam("userId") String userId,
                                       @RequestParam("title") String title,
                                       @RequestParam("description") String description,
                                       @RequestParam("mainCharacters") String mainCharacters,
                                       @RequestParam("category") String category,
                                       @RequestParam("tags") String tags,
                                       @RequestParam("targetAudience") String targetAudience,
                                       @RequestParam("language") String language,
                                       @RequestParam("copyright") String copyright,
                                       @RequestParam("isMature") String isMature,
                                       @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
                                       Model model) {

        System.out.println("----------------------------");
        System.out.println("UserId: " + userId);
        System.out.println("Title: " + title);

        // Better handling of cover image
        boolean hasValidCoverImage = coverImage != null && !coverImage.isEmpty() && coverImage.getSize() > 0;
        System.out.println("Has valid cover image: " + hasValidCoverImage);

        if (hasValidCoverImage) {
            System.out.println("Cover Image Size: " + coverImage.getSize());
            System.out.println("Cover Image Name: " + coverImage.getOriginalFilename());
        }

        try {
            User user = userService.getUserById(Integer.parseInt(userId));

            Story story = new Story();
            story.setTitle(title);
            story.setCategory(category);
            story.setDescription(description);
            story.setCopyright(copyright);
            story.setLanguage(language);
            story.setMature(Integer.parseInt(isMature));
            story.setTags(tags);
            story.setTotalPages(1);
            story.setUser(user);
            story.setTargetAudience(targetAudience);

            // Only process file upload if a valid file is provided
            if (hasValidCoverImage) {
                try {
                    String originalFileName = coverImage.getOriginalFilename();
                    System.out.println("original file name: "+originalFileName);
                    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    System.out.println("extension: "+extension);

                    String sanitizedTitle = title.replaceAll("[^a-zA-Z0-9]", "_");
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
                    coverImage.transferTo(destinationFile);
                    story.setImagePath(uniqueFileName);
                    System.out.println("Successfully uploaded cover image: " + uniqueFileName);
                } catch (Exception fileException) {
                    System.err.println("Error processing cover image: " + fileException.getMessage());
                    // Continue without the image rather than failing completely
                }
            } else {
                System.out.println("No cover image provided, creating story without image");
            }

            Story savedStory = storyService.addANewStory(story);
            System.out.println("Saved story: " + savedStory);

            int chapterNo = 1;
            List<Chapter> chapterList = chapterService.getChaptersListByStory(savedStory);
            for (Chapter x : chapterList){
                chapterNo++;
            }

            model.addAttribute("story",savedStory);
            model.addAttribute("chapterNo",chapterNo);

            return "chapter-edit-page";

        } catch (Exception e) {
            System.err.println("Error creating story: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Failed to create story: " + e.getMessage());
            return "error-page";
        }
    }
}