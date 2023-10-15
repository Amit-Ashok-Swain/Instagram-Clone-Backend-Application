package com.geekster.InstagramClone.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstagramFollower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followerTableId;

    @OneToOne
    private User user;

    @OneToOne
    private User follower;



}
