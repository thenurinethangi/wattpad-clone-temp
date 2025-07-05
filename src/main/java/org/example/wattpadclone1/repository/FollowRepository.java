package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.Follow;
import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {

    Follow findByUser(User currentUser);
}
