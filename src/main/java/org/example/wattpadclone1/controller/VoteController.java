package org.example.wattpadclone1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.entity.Vote;
import org.example.wattpadclone1.service.ChapterService;
import org.example.wattpadclone1.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private ChapterService chapterService;

    @GetMapping("add")
    public ResponseEntity<Map<String, Object>> vote(@RequestParam String chapterId, HttpSession session){

        //first check if current user already voted or not. if already voted then remove the vote
        User user = (User) session.getAttribute("currentUser");

        Chapter chapter = chapterService.getChapterById(Integer.parseInt(chapterId));

        Vote vote = new Vote(user,chapter);
        Vote vote1 = voteService.search(vote);

        if(vote1==null){
            voteService.addVote(vote);
            System.out.println("add vote");
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "add vote");
            return ResponseEntity.ok(response);
        }
        else{
           voteService.removeVote(vote1);
            System.out.println("remove vote");
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "remove vote");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("check")
    public ResponseEntity<Map<String, Object>> checkIfAlreadyVoteOrNot(@RequestParam String chapterId, HttpSession session){

        //first check if current user already voted or not for when ui load make vote icon right way
        User user = (User) session.getAttribute("currentUser");

        Chapter chapter = chapterService.getChapterById(Integer.parseInt(chapterId));

        Vote vote = new Vote(user,chapter);
        Vote vote1 = voteService.search(vote);

        if(vote1==null){
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "not voted");
            return ResponseEntity.ok(response);
        }
        else{
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "voted");
            return ResponseEntity.ok(response);
        }
    }
}
