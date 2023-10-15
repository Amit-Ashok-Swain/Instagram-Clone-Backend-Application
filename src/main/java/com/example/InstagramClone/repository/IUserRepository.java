package com.example.InstagramClone.repository;

import com.example.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
}
