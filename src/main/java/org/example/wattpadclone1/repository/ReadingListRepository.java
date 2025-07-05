package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.ReadingList;
import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList,Integer> {

    List<ReadingList> findAllByUser(User user);
}
