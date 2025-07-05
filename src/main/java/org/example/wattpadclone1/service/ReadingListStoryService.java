package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.ReadingList;
import org.example.wattpadclone1.entity.ReadingListStory;
import org.example.wattpadclone1.repository.ReadingListStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingListStoryService {

    @Autowired
    private ReadingListStoryRepository readingListStoryRepository;

    public List<ReadingListStory> getAllReadingListStoriesByReadingList(ReadingList x) {

        return readingListStoryRepository.findAllByReadingList(x);
    }
}
