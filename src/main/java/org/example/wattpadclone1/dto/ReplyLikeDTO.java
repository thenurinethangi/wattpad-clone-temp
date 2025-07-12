package org.example.wattpadclone1.dto;

import lombok.*;
import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.CommentChapter;
import org.example.wattpadclone1.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyLikeDTO {

    private Long id;
    private Comment comment;
    private CommentChapter commentChapter;
    private User user;
    private String replyMessage;
    private int likes;
    private LocalDateTime createdAt;
    private int isCurrentUserLiked;
}
