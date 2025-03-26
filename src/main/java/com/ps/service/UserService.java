package com.ps.service;

import com.ps.exception.UserException;
import com.ps.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public User findUserById(Integer userId) throws Exception;
    public User findUserByEmailId(String email) throws UserException;
    public User followUser(Integer userId1, Integer userId2) throws UserException;
    public User updateuser(User user,Integer userId) throws UserException;
    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);

}
