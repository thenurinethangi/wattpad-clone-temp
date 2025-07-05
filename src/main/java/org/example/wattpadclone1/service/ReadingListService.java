package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.ReadingList;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingListService {

    @Autowired
    private ReadingListRepository readingListRepository;

    public List<ReadingList> getReadingListByUser(User user) {

        return readingListRepository.findAllByUser(user);
    }
}
