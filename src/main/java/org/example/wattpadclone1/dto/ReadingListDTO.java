package org.example.wattpadclone1.dto;

import lombok.*;
import org.example.wattpadclone1.entity.ReadingListStory;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReadingListDTO {

    private Integer id;
    private User user;
    private String name;
    private int storyCount = 0;
    private LocalDateTime createdDate;
    private List<ReadingListStory> readingListStories;
}
