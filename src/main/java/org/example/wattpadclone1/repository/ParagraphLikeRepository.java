package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.ParagraphLike;
import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphLikeRepository extends JpaRepository<ParagraphLike,Integer> {

    ParagraphLike findByCommentAndUser(Comment comment, User user);
}
