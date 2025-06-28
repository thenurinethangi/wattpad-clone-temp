package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {

    List<Chapter> findAllByStory(Story story);
}
