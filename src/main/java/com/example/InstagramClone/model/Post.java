package com.example.InstagramClone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;



@Data
@Entity
@NoArgsConstructor
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String postData;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(Timestamp createdDate, Timestamp updatedDate, String postData, User user) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.postData = postData;
        this.user = user;
    }
}
