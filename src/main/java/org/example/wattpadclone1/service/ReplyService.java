package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Comment;
import org.example.wattpadclone1.entity.Reply;
import org.example.wattpadclone1.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getAllRepliesForSpecificComment(Comment comment) {

        return replyRepository.findAllByComment(comment);
    }

    public Reply getReplyById(int id) {

        return replyRepository.findById(id).orElse(null);
    }

    public Reply updateReply(Reply reply) {

        return replyRepository.save(reply);
    }
}
