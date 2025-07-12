package org.example.wattpadclone1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wattpadclone1.dto.ReplyLikeDTO;
import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.ParagraphLike;
import org.example.wattpadclone1.entity.Reply;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.service.CommentService;
import org.example.wattpadclone1.service.ParagraphLikeService;
import org.example.wattpadclone1.service.ReplyService;
import org.example.wattpadclone1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ParagraphLikeService paragraphLikeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ReplyLikeDTO>> getRepliesForSpecificComment(@RequestParam String commentId, HttpSession session){

        User user = (User) session.getAttribute("currentUser");

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));

        List<Reply> replyList = replyService.getAllRepliesForSpecificComment(comment);
        List<ReplyLikeDTO> replyLikeDTOList = new ArrayList<>();
        for (Reply x : replyList){
            ParagraphLike paragraphLike = paragraphLikeService.checkCurrentUserLikeOrNot(x,user);
            int isCurrentUserLiked = 0;
            if(paragraphLike!=null){
                isCurrentUserLiked = 1;
            }
            ReplyLikeDTO replyLikeDTO = new ReplyLikeDTO(x.getId(),x.getComment(),x.getCommentChapter(),x.getUser(),x.getReplyMessage(),x.getLikes(),x.getCreatedAt(),isCurrentUserLiked);
            replyLikeDTOList.add(replyLikeDTO);
        }
        return ResponseEntity.ok(replyLikeDTOList);
    }


    @PostMapping("like")
    public ResponseEntity<Map<String, String>> likeToComment(@RequestParam String replyId, @RequestParam String currentUserId){

        Reply reply = replyService.getReplyById(Integer.parseInt(replyId));

        User currentUser = userService.getUserById(Integer.parseInt(currentUserId));

        ParagraphLike paragraphLike = new ParagraphLike(currentUser,reply);
        ParagraphLike savedParagraphLike = paragraphLikeService.checkIfUserAlreadyLikeOrNotToReply(paragraphLike);

        Map<String,String> response = new HashMap<>();
        if(savedParagraphLike==null){
            ParagraphLike savedLike = paragraphLikeService.addNewLike(paragraphLike);
            int likes = reply.getLikes();
            likes++;
            reply.setLikes(likes);
            Reply updatedReply = replyService.updateReply(reply);
            response.put("status","200");
            response.put("response","success");
//            response.put("paragraphId", String.valueOf(comment.getParagraph().getId()));
            response.put("likedOrUnliked","Liked");
        }
        else{
            paragraphLikeService.removeLike(savedParagraphLike);
            int likes = reply.getLikes();
            likes--;
            reply.setLikes(likes);
            Reply updatedReply = replyService.updateReply(reply);
            response.put("status","200");
            response.put("response","success");
//            response.put("paragraphId", String.valueOf(comment.getParagraph().getId()));
            response.put("likedOrUnliked","Unliked");
        }

        return ResponseEntity.ok(response);
    }


    @PostMapping("put")
    public ResponseEntity<Reply> putReply(@RequestParam String currentUserId, @RequestParam String commentId, @RequestBody Map<String,Object> body){

        User currentUser = userService.getUserById(Integer.parseInt(currentUserId));

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));

        Reply reply = new Reply(comment,currentUser, (String) body.get("replyMessage"));
        Reply savedReply = replyService.addNewReply(reply);

        int replyCount = comment.getReplyCount();
        replyCount++;
        comment.setReplyCount(replyCount);
        commentService.updateComment(comment);

        if(savedReply!=null){
            return ResponseEntity.ok(savedReply);
        }
        return ResponseEntity.internalServerError().body(null);
    }
}


















