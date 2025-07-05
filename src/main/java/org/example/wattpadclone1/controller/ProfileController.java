package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.dto.ReadingListDTO;
import org.example.wattpadclone1.entity.ReadingList;
import org.example.wattpadclone1.entity.ReadingListStory;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private ReadingListService readingListService;
    @Autowired
    private ReadingListStoryService readingListStoryService;

    @GetMapping("{id}")
    public String getProfileById(@PathVariable String id, Model model){

        User user = profileService.getProfileById(Integer.parseInt(id));
        List<Story> storyList = storyService.getAllStoriesOfAUserWrite(user);

        int publishedStoriesCount = 0;
        int draftStoriesCount = 0;
        for (Story x : storyList){
            if(x.getPublishedOrDraft().equalsIgnoreCase("published")){
                publishedStoriesCount++;
            }
            else {
                draftStoriesCount++;
            }
        }

        List<ReadingList> readingLists = readingListService.getReadingListByUser(user);
        boolean isReadingListExist = false;
        for (ReadingList x : readingLists){
            if(x.getStoryCount()>0){
                isReadingListExist = true;
                break;
            }
        }

        int readingListCount = 0;
        List<ReadingListDTO> readingListDTOList = new ArrayList<>();
        for (ReadingList x : readingLists){
            if(x.getStoryCount()>0){
                readingListCount++;
                List<ReadingListStory> readingListStories = readingListStoryService.getAllReadingListStoriesByReadingList(x);
                readingListDTOList.add(new ReadingListDTO(x.getId(),x.getUser(),x.getName(),x.getStoryCount(),x.getCreatedDate(),readingListStories));
            }
        }

        model.addAttribute("profile",user);
        model.addAttribute("publishedStoriesCount",publishedStoriesCount);
        model.addAttribute("draftStoriesCount",draftStoriesCount);
        model.addAttribute("ownStories",storyList);
        model.addAttribute("isReadingListExist",isReadingListExist);
        model.addAttribute("readingListCount",readingListCount);
        if(isReadingListExist){
            model.addAttribute("readingLists",readingListDTOList);
        }

        return "user-profile";
    }
}
