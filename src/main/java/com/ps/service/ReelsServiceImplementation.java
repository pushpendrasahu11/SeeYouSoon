package com.ps.service;

import com.ps.model.Reels;
import com.ps.model.User;
import com.ps.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService {

    @Autowired
    ReelsRepository reelsRepository;

    @Autowired
    UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createdReel = new Reels();

        createdReel.setTitle(reel.getTitle());
        createdReel.setUser(user);
        createdReel.setVideo(reel.getVideo());

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) throws Exception {

        userService.findUserById(userId);


        return reelsRepository.findByUserId(userId);
    }
}
