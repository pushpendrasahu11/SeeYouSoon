package com.ps.service;

import com.ps.model.Reels;
import com.ps.model.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUserReels(Integer userId) throws Exception;
}
