package com.ps.controller;


import com.ps.model.Story;
import com.ps.model.User;
import com.ps.service.StoryService;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){

        Story createdStory = storyService.createStory(story,userService.findUserByJwt(jwt));
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserById(userId);

        List<Story> userStory = storyService.findStoryByUserId(userId);

        return userStory ;

    }

    


}
