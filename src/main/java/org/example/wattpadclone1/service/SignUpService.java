package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;

    public User addUser(User user) {

        try {
            return signUpRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
