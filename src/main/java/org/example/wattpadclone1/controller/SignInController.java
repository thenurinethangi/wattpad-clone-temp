package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Library;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.HomeService;
import org.example.wattpadclone1.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/signin")
public class SignInController {

    @Autowired
    private SignInService signInService;
    @Autowired
    private HomeService homeService;

    @PostMapping
    public String authentication(@ModelAttribute User user, Model model){

        System.out.println(user);
        User user1 = signInService.isExist(user.getUsername());
        System.out.println(user1);

        if(user1!=null){

            String p = user1.getPassword();
            if(p.equals(user.getPassword( ))){
                List<Story> stories = new ArrayList<>();
                List<Library>  libraries = homeService.getLibraryStories(user1);
                for (Library x : libraries){
                    System.out.println(x);
                    System.out.println("story: "+x.getStory());
                    stories.add(x.getStory());
                }
                model.addAttribute("libraryStories",stories);

                List<Story> storiesNotInLb = new ArrayList<>();
                List<Story>  storyList = homeService.getAllStories();
                for (Story x : storyList){
                    boolean z = true;
                    for (Story y : stories){
                        if(x.getId()==y.getId()){
                            z = false;
                            break;
                        }
                    }
                    if(z){
                      storiesNotInLb.add(x);
                    }
                }
                System.out.println("====================================================");
                System.out.println(storiesNotInLb);
                model.addAttribute("storiesNotInLb",storiesNotInLb);

                return "home-page";
            }

            else{
                System.out.println("password incorrect");
                return "sign-in";
            }
        }
        else{
            System.out.println("user not exist");
            return "sign-up";
        }
    }
}
