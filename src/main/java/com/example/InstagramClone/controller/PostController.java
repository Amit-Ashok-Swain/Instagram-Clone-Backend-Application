package com.example.InstagramClone.controller;

import com.example.InstagramClone.model.Post;
import com.example.InstagramClone.service.PostService;
import com.example.InstagramClone.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;


    @Autowired
    TokenService authService;

    @PostMapping()
    public ResponseEntity<Void> addPost(@RequestParam String email, @RequestParam String token, @RequestBody Post post) {
        HttpStatus status;
        if(authService.authenticate(email,token))
        {
            postService.addPost(post);
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<Void>(status);

    }

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPost(@RequestParam String email, @RequestParam String token) {
        HttpStatus status;
        List<Post> postList = null;
        if(authService.authenticate(email,token))
        {

            postList =  postService.getAllPosts(token);
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }



        return new ResponseEntity<List<Post>>(postList, status);
    }
}
