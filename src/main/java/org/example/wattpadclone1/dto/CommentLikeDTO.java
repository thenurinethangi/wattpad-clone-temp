package org.example.wattpadclone1.dto;

import lombok.*;
import org.example.wattpadclone1.entity.ChapterMedia;
import org.example.wattpadclone1.entity.Paragraph;
import org.example.wattpadclone1.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CommentLikeDTO {

    private Long id;
    private Paragraph paragraph;
    private ChapterMedia media;
    private User user;
    private String commentMessage;
    private int likes;
    private int replyCount;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int isCurrentUserLiked;
}
