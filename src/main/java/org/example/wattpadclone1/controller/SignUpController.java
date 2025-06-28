package org.example.wattpadclone1.controller;

import org.example.wattpadclone1.dto.UserDTO;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/api/v1/signup")
@CrossOrigin
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public String addUser(@ModelAttribute UserDTO userDTO, Model model){

        User user = new User(userDTO.getUsername(),userDTO.getFullName(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getRole(),userDTO.getImagePath());
        User user1 = signUpService.addUser(user);

        if(user1!=null){
            return "sign-in";
        }

        return "error-page";
    }
}
