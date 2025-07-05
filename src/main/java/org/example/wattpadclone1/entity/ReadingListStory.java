package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reading_list_story")
public class ReadingListStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reading_list_id", nullable = false)
    private ReadingList readingList;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column(name = "added_date", updatable = false)
    private LocalDateTime addedDate = LocalDateTime.now();
}

