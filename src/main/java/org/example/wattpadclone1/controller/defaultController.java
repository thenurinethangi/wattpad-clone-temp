package org.example.wattpadclone1.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller()
@RequestMapping("/*")
public class defaultController {

    @GetMapping
    public ResponseEntity<String> defaultMap(){

//        System.out.println("hello");
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
