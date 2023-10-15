package com.geekster.InstagramClone.service;

import com.geekster.InstagramClone.model.InstagramFollowing;
import com.geekster.InstagramClone.model.User;
import com.geekster.InstagramClone.repository.IFollowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {

    @Autowired
    IFollowingRepo followingRepo;

    public void saveFollowing(User myUser, User otherUser) {
        InstagramFollowing followingRecord = new InstagramFollowing(null,myUser,otherUser);
        followingRepo.save(followingRecord);
    }
}
