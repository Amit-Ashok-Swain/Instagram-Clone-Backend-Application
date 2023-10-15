package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.InstagramComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<InstagramComment, Long> {
}
