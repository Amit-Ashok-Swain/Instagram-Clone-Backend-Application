package com.geekster.InstagramClone.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstagramFollowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followingTableId;

    @OneToOne
    private User user;

    @OneToOne
    private User following;
}
