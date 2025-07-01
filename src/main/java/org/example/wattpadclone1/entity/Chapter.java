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
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    private String title;

    private LocalDateTime publishDate;

    private String coverImage;

    @Column(name = "views")
    private String views;

    @Column(name = "votes")
    private String votes;

    @Column(name = "comments")
    private String comments;
}
