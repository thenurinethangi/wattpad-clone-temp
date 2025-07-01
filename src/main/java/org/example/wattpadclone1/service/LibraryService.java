package org.example.wattpadclone1.service;

import org.example.wattpadclone1.entity.Library;
import org.example.wattpadclone1.entity.User;
import org.example.wattpadclone1.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public Library getLibraryById(int id) {
        return libraryRepository.findById(id).orElse(null);
    }

    public List<Library> getAllLibraryByUser(User user) {
        return libraryRepository.findAllByUser(user);
    }
}
