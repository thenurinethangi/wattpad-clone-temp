package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Vote;
import org.example.wattpadclone1.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote search(Vote vote) {

        return voteRepository.findByUserAndChapter(vote.getUser(),vote.getChapter());
    }

    public void addVote(Vote vote) {

        voteRepository.save(vote);
    }

    public void removeVote(Vote vote1) {

        voteRepository.delete(vote1);
    }
}
