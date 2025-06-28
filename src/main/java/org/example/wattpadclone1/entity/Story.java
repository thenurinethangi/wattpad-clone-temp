package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "story")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String category;

    @Column(length = 255)
    private String tags;

    @Column(name = "target_audience", length = 100)
    private String targetAudience;

    @Column(length = 50)
    private String language;

    @Column(length = 100)
    private String copyright;

    @Column(length = 50)
    private String rating;

    @Column(name = "main_characters", columnDefinition = "TEXT")
    private String mainCharacters;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Column(name = "total_pages", columnDefinition = "INT DEFAULT 0")
    private int totalPages = 0;

    @Column(name = "status")
    private String status = "Ongoing";

    @Column(name = "mature")
    private int mature = 0;

    @Column(name = "reads")
    private String reads;

    @Column(name = "votes")
    private String votes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
