package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.ChapterMedia;
import org.example.wattpadclone1.repository.ChapterMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterMediaService {

    @Autowired
    private ChapterMediaRepository chapterMediaRepository;

    public ChapterMedia addMediaForAChapter(ChapterMedia chapterMedia) {
        return chapterMediaRepository.save(chapterMedia);
    }
}
