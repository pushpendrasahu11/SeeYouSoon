package com.ps.repository;

import com.ps.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {


    public List<Story> findByUserId(Integer userId);


}
