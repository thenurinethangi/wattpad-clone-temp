package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.service.ChapterService;
import org.example.wattpadclone1.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/story/overview")
public class StoryOverViewController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private ChapterService chapterService;

    @GetMapping()
    public String getOverviewOfAStory(@RequestParam String id, Model model){

        Story story = storyService.getStoryDetailsById(Integer.parseInt(id));

        if(story!=null){
            model.addAttribute("story",story);

            List<Chapter> chapterList = chapterService.getChaptersListByStory(story);
            model.addAttribute("chapters",chapterList);

            return "story-overview-page";
        }
        else{
            return "error-page";
        }
    }
}
