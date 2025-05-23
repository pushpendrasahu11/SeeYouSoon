package com.ps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String content;

    private String image;

    @ManyToOne
    private User user;

    @JsonIgnore // so chat will not show when response json , hence will not make a infinte response with message having chat and chat having message
    @ManyToOne
    private Chat chat; // one chat can have man messages

    private LocalDateTime timeStamp;

    public Message(){}
    public Message(Integer id, String content, String image, User user, Chat chat, LocalDateTime timeStamp) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.user = user;
        this.chat = chat;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
