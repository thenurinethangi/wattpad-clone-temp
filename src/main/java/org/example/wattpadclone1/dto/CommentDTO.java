package org.example.wattpadclone1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CommentDTO {

    private int chapterId;
    private int currentUserId;
    private int paragraphId;
    private String commentMessage;
}
