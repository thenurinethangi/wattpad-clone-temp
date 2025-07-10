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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paragraph_id", referencedColumnName = "id", nullable = true)
    private Paragraph paragraph;

    @ManyToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id", nullable = true)
    private ChapterMedia media;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "comment_message", columnDefinition = "TEXT")
    private String commentMessage;

    @Column(name = "likes")
    private int likes = 0;

    @Column(name = "reply_count")
    private int replyCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Comment(Paragraph paragraph, ChapterMedia media, User user, String commentMessage) {
        this.paragraph = paragraph;
        this.media = media;
        this.user = user;
        this.commentMessage = commentMessage;
    }
}

