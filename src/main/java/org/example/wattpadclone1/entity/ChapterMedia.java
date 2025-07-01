package org.example.wattpadclone1.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "chapter_media")
public class ChapterMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "media", nullable = false)
    private String media;

    @Column(name = "sequence_no", nullable = false)
    private int sequenceNo;

    @ManyToOne
    @JoinColumn(name = "chapter_id",nullable = false)
    private Chapter chapter;

    public ChapterMedia(String media, int sequenceNo, Chapter chapter) {
        this.media = media;
        this.sequenceNo = sequenceNo;
        this.chapter = chapter;
    }
}

