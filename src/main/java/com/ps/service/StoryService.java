package com.ps.service;

import com.ps.model.Story;
import com.ps.model.User;

import java.util.List;

public interface StoryService {

    public Story createStory (Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
