package com.example.InstagramClone.repository;

import com.example.InstagramClone.model.Post;
import com.example.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
}
