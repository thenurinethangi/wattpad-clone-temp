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
@Table(name = "comment_chapter")
public class CommentChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chapter_id", referencedColumnName = "id", nullable = false)
    private Chapter chapter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "comment_message", columnDefinition = "TEXT")
    private String commentMessage;

    @Column(name = "likes")
    private int likes = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

