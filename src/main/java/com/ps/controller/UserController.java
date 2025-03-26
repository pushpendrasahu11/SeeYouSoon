package com.ps.controller;

import com.ps.model.User;
import com.ps.repository.UserRepository;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    
    @GetMapping("/api/users")
    public  List<User> getUsers(){
        List<User> users = userRepository.findAll();

        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {
             return userService.findUserById(userId);
    }

    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        return userService.updateuser(user,reqUser.getId());
    }

    @DeleteMapping("/api/users/{userId}")
    public User deleteUser(@PathVariable Integer userId) throws Exception {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new Exception("user not exist with id " + userId);
        }

        userRepository.delete(user.get());

        return user.get();


    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        return userService.searchUser(query);
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

//        System.out.println("jwt --------" + jwt);
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
