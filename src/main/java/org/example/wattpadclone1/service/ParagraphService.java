package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Paragraph;
import org.example.wattpadclone1.entity.ParagraphLike;
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

    public Paragraph addParagraphForAChapter(Paragraph paragraph) {

        return paragraphRepository.save(paragraph);
    }

    public Paragraph getParagraphById(int id) {

        return paragraphRepository.findById(id).orElse(null);
    }
}
