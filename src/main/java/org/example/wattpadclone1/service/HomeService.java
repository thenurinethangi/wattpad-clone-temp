package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Library;
import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.HomeRepository;
import org.example.wattpadclone1.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private StoryRepository storyRepository;

    public List<Library> getLibraryStories(User user1) {
        return homeRepository.findAllByUserId(user1.getId());
    }

    public List<Story> getAllStories() {

        return storyRepository.findAll();
    }
}
