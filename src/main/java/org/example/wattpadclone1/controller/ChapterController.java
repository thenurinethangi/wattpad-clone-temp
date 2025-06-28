package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Paragraph;
import org.example.wattpadclone1.service.ChapterService;
import org.example.wattpadclone1.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ParagraphService paragraphService;

    @GetMapping("firstChapter")
    public String getFirstChapterOfStory(@RequestParam String id, Model model){

        Chapter chapter = chapterService.getChapterById(Integer.parseInt(id));
        System.out.println("cha cha chap----------- "+chapter);

        if(chapter==null){
            System.out.println("no chapters in this story");
            return "error-page";
        }

        List<Paragraph> paragraphList = paragraphService.getAllParagraphsOfSelectedChapter(chapter);
        System.out.println("paraaaaaaaaaaaaaaaaaa"+paragraphList);
        model.addAttribute("chapter",chapter);
        model.addAttribute("paragraphs",paragraphList);

        return "chapters-page";
    }
}
