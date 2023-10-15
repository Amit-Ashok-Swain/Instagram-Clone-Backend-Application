package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.InstagramFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowingRepo extends JpaRepository<InstagramFollowing, Long> {

    Long countByUser_userId(long userId);
}
