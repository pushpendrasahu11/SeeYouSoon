package com.ps.controller;

import com.ps.model.Reels;
import com.ps.service.ReelsService;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels (@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        Reels createdReels = reelsService.createReel(reel,userService.findUserByJwt(jwt));
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        return reelsService.findAllReels();
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        return reelsService.findUserReels(userId);
    }



}
