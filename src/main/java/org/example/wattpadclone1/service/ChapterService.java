package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getChaptersListByStory(Story story) {

        return chapterRepository.findAllByStory(story);
    }

    public Chapter getChapterById(int id) {

        return chapterRepository.findById(id).orElse(null);
    }

    public Chapter saveNewChapterForStory(Chapter chapter) {

        return chapterRepository.save(chapter);
    }

    public Chapter increaseViews(Chapter chapter) {

        return chapterRepository.save(chapter);
    }
}
