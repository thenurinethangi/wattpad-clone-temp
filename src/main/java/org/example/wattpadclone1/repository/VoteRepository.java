package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Chapter;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {

    Vote findByUserAndChapter(User user, Chapter chapter);
}
