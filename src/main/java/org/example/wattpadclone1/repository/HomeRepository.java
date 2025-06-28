package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Library;
import org.example.wattpadclone1.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Library,Integer> {

    List<Library> findAllByUserId(int id);
}
