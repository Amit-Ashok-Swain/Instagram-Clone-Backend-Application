package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    User findByUserId(Long id);
}
