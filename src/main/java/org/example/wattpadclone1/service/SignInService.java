package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.SignInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private SignInRepository signInRepository;

    public User isExist(String username) {

        return signInRepository.findByUsername(username);
    }
}
