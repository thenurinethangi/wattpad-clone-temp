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
@Table(name = "paragraph_like")
public class ParagraphLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

    public ParagraphLike(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public ParagraphLike(User user, Reply reply) {
        this.user = user;
        this.reply = reply;
    }
}

