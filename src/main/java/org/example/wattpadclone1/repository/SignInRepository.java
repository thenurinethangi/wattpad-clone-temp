package org.example.wattpadclone1.repository;

import org.example.wattpadclone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
