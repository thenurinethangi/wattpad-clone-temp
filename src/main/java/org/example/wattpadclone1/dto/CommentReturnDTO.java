package org.example.wattpadclone1.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CommentReturnDTO {

    private int paragraphId;
    private String userName;
    private String userImagePath;
    private String commentMessage;
    private LocalDateTime commentedTime;
    private int likes;
    private int isAuthor;
}
