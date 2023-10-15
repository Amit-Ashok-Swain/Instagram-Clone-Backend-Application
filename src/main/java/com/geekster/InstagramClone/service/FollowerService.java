package com.geekster.InstagramClone.service;


import com.geekster.InstagramClone.model.InstagramFollower;
import com.geekster.InstagramClone.model.User;
import com.geekster.InstagramClone.repository.IFollowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

    @Autowired
    IFollowerRepo followerRepo;

    public void saveFollower(User myUser, User otherUser) {


        InstagramFollower follower = new InstagramFollower(null,myUser,otherUser);

        followerRepo.save(follower);
    }
}
