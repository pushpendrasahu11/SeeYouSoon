package com.ps.controller;

import com.ps.model.Comment;
import com.ps.model.User;
import com.ps.service.CommentService;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("api/comment/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(comment,postId,user.getId());

        return createdComment;

    }

    @PutMapping("api/comment/like/{commentId}")
    public Comment likeComment( @RequestHeader("Authorization") String jwt, @PathVariable Integer commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId,user.getId());

        return likedComment;

    }

}
