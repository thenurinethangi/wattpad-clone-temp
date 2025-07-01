package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Story;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    public Story getStoryDetailsById(int id) {
        Optional<Story> story = storyRepository.findById(id);

        return story.orElse(null);
    }

    public Story addANewStory(Story story) {

        return storyRepository.save(story);
    }

    public List<Story> getAllStoriesOfAUserWrite(User user) {

        return storyRepository.findAllByUser(user);
    }
}
