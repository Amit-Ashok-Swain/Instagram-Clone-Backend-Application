package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.Post;
import com.geekster.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

}
