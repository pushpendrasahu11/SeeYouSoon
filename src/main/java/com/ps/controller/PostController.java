package com.ps.controller;

import com.ps.config.JwtProvider;
import com.ps.model.Post;
import com.ps.response.ApiResponse;
import com.ps.service.PostService;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("api/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception {
        Post createdPost = postService.createNewPost(post, userService.findUserByJwt(jwt).getId());

        return new ResponseEntity<Post>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {

        String message = postService.deletePost(postId,userService.findUserByJwt(jwt).getId());
        ApiResponse res = new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.FOUND);

    }

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){

        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);

    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost(){

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);

    }

    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {

        Post post = postService.savedPost(postId,userService.findUserByJwt(jwt).getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);

    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {

        Post post = postService.likePost(postId,userService.findUserByJwt(jwt).getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);

    }

}
