package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Follow;
import org.example.wattpadclone1.entity.Following;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    public List<Following> checkUserAccountAlreadyFollowedOrNot(Follow follow) {

        return followingRepository.findAllByFollow(follow);
    }

    public void removeUserFromFollowingList(Following x) {

        followingRepository.delete(x);
    }

    public Following addUserToFollowingList(Following following) {

        return followingRepository.save(following);
    }
}
