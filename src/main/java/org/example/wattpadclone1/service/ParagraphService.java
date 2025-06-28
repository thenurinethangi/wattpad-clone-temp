package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Paragraph;
import org.example.wattpadclone1.repository.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParagraphService {

    @Autowired
    private ParagraphRepository paragraphRepository;

    public List<Paragraph> getAllParagraphsOfSelectedChapter(Chapter chapter) {

        return paragraphRepository.findAllByChapter(chapter);
    }
}
