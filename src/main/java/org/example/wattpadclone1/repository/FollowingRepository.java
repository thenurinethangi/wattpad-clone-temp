package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Follow;
import org.example.wattpadclone1.entity.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Integer> {

    List<Following> findAllByFollow(Follow follow);
}
