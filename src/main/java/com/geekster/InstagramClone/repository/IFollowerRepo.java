package com.geekster.InstagramClone.repository;


import com.geekster.InstagramClone.model.InstagramFollower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowerRepo extends JpaRepository<InstagramFollower, Long> {


}
