package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.ParagraphLike;
import org.example.wattpadclone1.entity.Reply;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.ParagraphLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParagraphLikeService {

    @Autowired
    private ParagraphLikeRepository paragraphLikeRepository;

    public ParagraphLike addNewLike(ParagraphLike paragraphLike) {

        return paragraphLikeRepository.save(paragraphLike);
    }

    public ParagraphLike checkIfUserAlreadyLikeOrNot(ParagraphLike paragraphLike) {

        return paragraphLikeRepository.findByCommentAndUser(paragraphLike.getComment(),paragraphLike.getUser());
    }

    public void removeLike(ParagraphLike paragraphLike) {

        paragraphLikeRepository.delete(paragraphLike);
    }

    public ParagraphLike checkCurrentUserLikeOrNot(Reply reply, User user) {

        return paragraphLikeRepository.findByReplyAndUser(reply,user);
    }

    public ParagraphLike checkIfUserAlreadyLikeOrNotToReply(ParagraphLike paragraphLike) {

        return paragraphLikeRepository.findByReplyAndUser(paragraphLike.getReply(),paragraphLike.getUser());
    }
}
