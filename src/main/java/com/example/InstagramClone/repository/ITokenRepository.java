package com.example.InstagramClone.repository;

import com.example.InstagramClone.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByToken(String token);
}
