package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.Paragraph;
import org.example.wattpadclone1.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addANewComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsByParagraph(Paragraph paragraph) {

        return commentRepository.findAllByParagraph(paragraph);
    }

    public Comment getCommentById(int id) {

        return commentRepository.findById(id).orElse(null);
    }

    public Comment updateComment(Comment comment) {

        return commentRepository.save(comment);
    }
}
