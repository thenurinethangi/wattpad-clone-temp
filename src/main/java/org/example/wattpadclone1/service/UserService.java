package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) {

        return userRepository.findById(id).orElse(null);
    }
}
