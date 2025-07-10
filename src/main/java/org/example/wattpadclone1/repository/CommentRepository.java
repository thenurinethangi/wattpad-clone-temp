package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByParagraph(Paragraph paragraph);
}
