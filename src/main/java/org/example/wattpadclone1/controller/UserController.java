package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.UserRepository;
import org.example.wattpadclone1.service.StoryService;
import org.example.wattpadclone1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StoryService storyService;

    @GetMapping("{id}")
    public String getUserProfileByUserId(@PathVariable String id, Model model){

        User user = userService.getUserById(Integer.parseInt(id));
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

        model.addAttribute("user",user);
        model.addAttribute("publishedStoriesCount",publishedStoriesCount);
        model.addAttribute("draftStoriesCount",draftStoriesCount);
        model.addAttribute("ownStories",storyList);
        model.addAttribute("isReadingListExist",true);

        return "user-account";
    }
}


















