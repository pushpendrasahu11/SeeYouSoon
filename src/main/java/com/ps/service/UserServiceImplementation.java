package com.ps.service;

import com.ps.config.JwtProvider;
import com.ps.exception.UserException ;
import com.ps.exception.UserException;
import com.ps.model.User;
import com.ps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException  {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) return user.get();

        throw new UserException ("user not exist with userid "  + userId);

    }

    @Override
    public User findUserByEmailId(String email) throws UserException  {

        User user = userRepository.findByEmail(email);

        if(user != null) return user;

        throw new UserException ("user not exist with email "  + email);

    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws UserException  {

        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        

        return user2;
    }

    @Override
    public User updateuser(User user, Integer userId) throws UserException {

        Optional<User> oldUser = userRepository.findById(userId);

        if(oldUser.isEmpty()){
            throw new UserException ("user not exist with id " + userId);
        }

        if(user.getFirstName()!= null) oldUser.get().setFirstName(user.getFirstName());
        if(user.getLastName()!=null) oldUser.get().setLastName(user.getLastName());
        if(user.getEmail()!=null) oldUser.get().setEmail(user.getEmail());
        if(user.getPassword()!=null) oldUser.get().setPassword(user.getPassword());
        if(user.getGender()!=null) oldUser.get().setGender(user.getGender());
        User newUser = userRepository.save(oldUser.get());

        return newUser;
    }

    @Override
    public List<User> searchUser(String query) {


        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFormJwt(jwt);
        User user = userRepository.findByEmail(email);

        return user;
    }
}
