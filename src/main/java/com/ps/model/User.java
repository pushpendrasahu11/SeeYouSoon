package com.ps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id //unique identifier
    @GeneratedValue(strategy = GenerationType.AUTO)// it will generate unique id automatically
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Integer> followings = new ArrayList<>();
    private List<Integer> followers = new ArrayList<>();
    private String gender;
    @ManyToMany
    @JsonIgnore // loop ho raha tha json me post and user ka , so we user json ignore
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followings, List<Integer> followers, String gender, List<Post> savedPost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.followings = followings;
        this.followers = followers;
        this.gender = gender;
        this.savedPost = savedPost;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followings, List<Integer> followers, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.followings = followings;
        this.followers = followers;
        this.gender = gender;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
