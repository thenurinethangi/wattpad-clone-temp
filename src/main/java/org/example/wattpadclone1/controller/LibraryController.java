package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.entity.Library;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.LibraryService;
import org.example.wattpadclone1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserService userService ;

    @GetMapping("{id}")
    public String getLibrary(@PathVariable String id, Model model){

        User user = userService.getUserById(Integer.parseInt(id));
        List<Library> libraryList = libraryService.getAllLibraryByUser(user);
        model.addAttribute("libraryList",libraryList);

        return "library-page";
    }
}
