package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story,Integer> {

    List<Story> findAllByUser(User user);
}
