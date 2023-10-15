package com.geekster.InstagramClone.controller;


import com.geekster.InstagramClone.model.InstagramComment;
import com.geekster.InstagramClone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

   @PostMapping()
   public String addComment(@RequestBody InstagramComment comment)
   {
       return commentService.addComment(comment);
   }
}
