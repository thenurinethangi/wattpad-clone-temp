package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public User getProfileById(int id) {

        return profileRepository.findById(id).orElse(null);
    }
}
