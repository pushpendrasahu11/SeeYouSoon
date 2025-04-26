package com.ps.service;

import com.ps.model.Comment;
import com.ps.model.Post;
import com.ps.model.User;
import com.ps.repository.CommentRepository;
import com.ps.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{


    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {

        Comment comment = findCommentById(commentId);

        User user = userService.findUserById(userId);
        if(!comment.getliked().contains(user)) {
            comment.getliked().add(user);
        }else{
            comment.getliked().remove(user);
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {

        Optional<Comment> res = commentRepository.findById(commentId);

        if(res.isEmpty()){
                  throw new Exception("comment not exist");
        }

        return res.get();
    }
}
