package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column(name = "saved_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime savedAt = LocalDateTime.now();

    @Column(name = "last_read_page", columnDefinition = "INT DEFAULT 1")
    private int lastReadPage = 1;

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", savedAt=" + savedAt +
                ", lastReadPage=" + lastReadPage +
                '}';
    }
}
