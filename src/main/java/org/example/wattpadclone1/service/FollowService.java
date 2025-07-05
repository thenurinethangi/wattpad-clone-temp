package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Follow;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public Follow getUserFollowId(User currentUser) {

        return followRepository.findByUser(currentUser);
    }
}
