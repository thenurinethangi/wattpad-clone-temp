package org.example.wattpadclone1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.wattpadclone1.dto.CommentDTO;
import org.example.wattpadclone1.dto.CommentLikeDTO;
import org.example.wattpadclone1.dto.CommentReturnDTO;
import org.example.wattpadclone1.entity.*;
import org.example.wattpadclone1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private UserService userService;
    @Autowired
    private ParagraphService paragraphService;
    @Autowired
    private ParagraphLikeService paragraphLikeService;

    @PostMapping("put")
    public ResponseEntity<CommentReturnDTO> putComment(@RequestBody CommentDTO commentDTO){

        User currentUser = userService.getUserById(commentDTO.getCurrentUserId());

        Chapter chapter = chapterService.getChapterById(commentDTO.getChapterId());

        Paragraph paragraph = paragraphService.getParagraphById(commentDTO.getParagraphId());

        Comment comment = new Comment(paragraph,null,currentUser,commentDTO.getCommentMessage());
        Comment savedComment = commentService.addANewComment(comment);

        int isAuthor = 0;
        if(currentUser.getId()==chapter.getStory().getUser().getId()){
            isAuthor = 1;
        }

        CommentReturnDTO commentReturnDTO = new CommentReturnDTO(paragraph.getId(),chapter.getStory().getUser().getUsername(),chapter.getStory().getUser().getImagePath(),savedComment.getCommentMessage(),savedComment.getCreatedAt(),savedComment.getLikes(),isAuthor);
        return ResponseEntity.ok(commentReturnDTO);
    }

    @GetMapping("load")
    public ResponseEntity<List<CommentLikeDTO>> loadCommentByParagraph(@RequestParam String paragraphId, HttpSession session){

        User currentUser = (User) session.getAttribute("currentUser");

        Paragraph paragraph = paragraphService.getParagraphById(Integer.parseInt(paragraphId));

        List<Comment> commentList = commentService.getAllCommentsByParagraph(paragraph);
        commentList = commentList.reversed();

        List<CommentLikeDTO> commentLikeDTOList = new ArrayList<>();
        for (Comment x : commentList){
            ParagraphLike paragraphLike = new ParagraphLike(x,currentUser);
            ParagraphLike isExist = paragraphLikeService.checkIfUserAlreadyLikeOrNot(paragraphLike);
            int isCurrentUserLiked = 0;
            if(isExist!=null){
                isCurrentUserLiked = 1;
            }
            CommentLikeDTO commentLikeDTO = new CommentLikeDTO(x.getId(),x.getParagraph(),x.getMedia(),x.getUser(),x.getCommentMessage(),x.getLikes(),x.getReplyCount(),x.getCreatedAt(),isCurrentUserLiked);
            commentLikeDTOList.add(commentLikeDTO);
        }

        return ResponseEntity.ok(commentLikeDTOList);
    }

    @PostMapping("like")
    public ResponseEntity<Map<String, String>> likeToComment(@RequestParam String commentId,@RequestParam String currentUserId){

        Comment comment = commentService.getCommentById(Integer.parseInt(commentId));

        User currentUser = userService.getUserById(Integer.parseInt(currentUserId));

        ParagraphLike paragraphLike = new ParagraphLike(comment,currentUser);
        ParagraphLike savedParagraphLike = paragraphLikeService.checkIfUserAlreadyLikeOrNot(paragraphLike);

        Map<String,String> response = new HashMap<>();
        if(savedParagraphLike==null){
            ParagraphLike savedLike = paragraphLikeService.addNewLike(paragraphLike);
            int likes = comment.getLikes();
            likes++;
            comment.setLikes(likes);
            Comment updatedComment = commentService.updateComment(comment);
            response.put("status","200");
            response.put("response","success");
            response.put("paragraphId", String.valueOf(comment.getParagraph().getId()));
            response.put("likedOrUnliked","Liked");
        }
        else{
            paragraphLikeService.removeLike(savedParagraphLike);
            int likes = comment.getLikes();
            likes--;
            comment.setLikes(likes);
            Comment updatedComment = commentService.updateComment(comment);
            response.put("status","200");
            response.put("response","success");
            response.put("paragraphId", String.valueOf(comment.getParagraph().getId()));
            response.put("likedOrUnliked","Unliked");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("count")
    public ResponseEntity<Integer> checkEachParagraphCommentsCountByParagraphId(@RequestParam String paragraphId){

        Paragraph paragraph = paragraphService.getParagraphById(Integer.parseInt(paragraphId));
        List<Comment> commentList = commentService.getAllCommentsByParagraph(paragraph);

        int count = 0;
        for (Comment x : commentList){
            count++;
            count+=x.getReplyCount();
        }

        return ResponseEntity.ok(count);
    }
}

































