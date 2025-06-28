package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story,Integer> {

}
