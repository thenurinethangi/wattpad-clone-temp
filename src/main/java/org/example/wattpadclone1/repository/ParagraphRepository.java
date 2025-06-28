package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,Integer> {

    List<Paragraph> findAllByChapter(Chapter chapter);
}
