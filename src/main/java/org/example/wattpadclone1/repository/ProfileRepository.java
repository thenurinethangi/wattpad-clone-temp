package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<User,Integer> {
}
