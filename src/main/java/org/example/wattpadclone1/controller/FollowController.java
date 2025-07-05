package org.example.wattpadclone1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wattpadclone1.entity.Follow;
import org.example.wattpadclone1.entity.Following;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.FollowService;
import org.example.wattpadclone1.service.FollowingService;
import org.example.wattpadclone1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowingService followingService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> followOthers(@RequestParam String id, HttpSession session){

        User currentUser = (User) session.getAttribute("currentUser");

        Follow follow = followService.getUserFollowId(currentUser);
        List<Following> followings = followingService.checkUserAccountAlreadyFollowedOrNot(follow);

        boolean bool = false;
        for (Following x : followings){
            if(x.getUser().getId()==Integer.parseInt(id)){
                followingService.removeUserFromFollowingList(x);
                bool = true;
                break;
            }
        }

        if(!bool){
            User user = userService.getUserById(Integer.parseInt(id));
            Following following = new Following(follow,user);
            Following following2 = followingService.addUserToFollowingList(following);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Followed");
            return ResponseEntity.ok(response);
        }
        else{
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Follow");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("status")
    public ResponseEntity<Map<String, Object>> checkFollowStatus(@RequestParam String id, HttpSession session){

        User currentUser = (User) session.getAttribute("currentUser");
        User user = userService.getUserById(Integer.parseInt(id));

        if(user.getId()==currentUser.getId()){
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "myAccount");
            return ResponseEntity.ok(response);
        }

        Follow follow = followService.getUserFollowId(currentUser);
        List<Following> followings = followingService.checkUserAccountAlreadyFollowedOrNot(follow);

        boolean bool = false;
        for (Following x : followings){
            if(x.getUser().getId()==Integer.parseInt(id)){
                bool = true;
                break;
            }
        }

        if(!bool){
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Not Follow");
            return ResponseEntity.ok(response);
        }
        else{
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Follow");
            return ResponseEntity.ok(response);
        }
    }
}
