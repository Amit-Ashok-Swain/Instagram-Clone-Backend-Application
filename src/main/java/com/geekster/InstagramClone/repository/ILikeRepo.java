package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepo extends JpaRepository<PostLike, Long> {
    long countByPost_PostId(Long postId);
}
