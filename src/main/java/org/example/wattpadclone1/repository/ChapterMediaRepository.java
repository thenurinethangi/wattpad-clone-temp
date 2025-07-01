package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.ChapterMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterMediaRepository extends JpaRepository<ChapterMedia, Integer> {
}
