package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.ReadingList;
import org.example.wattpadclone1.entity.ReadingListStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListStoryRepository extends JpaRepository<ReadingListStory,Integer> {

    List<ReadingListStory> findAllByReadingList(ReadingList x);
}
