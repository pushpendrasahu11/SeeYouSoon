package com.ps.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String chat_name;
    private String chat_image;

    @ManyToMany
    private List<User> users = new ArrayList<>(); // two users in a chat

    @OneToMany(mappedBy = "chat") // ye dene so hoga ki ye chat and message ko map krne ke liye alaga table nahi karega, chat wale table se he kaam chal jayega
    private List<Message> messages = new ArrayList<>();

    private LocalDateTime timeStamp;

    public Chat(){

    }

    public Chat(Integer id, String chat_name, String chat_image, List<User> users, List<Message> messages, LocalDateTime timeStamp) {
        this.id = id;
        this.chat_name = chat_name;
        this.chat_image = chat_image;
        this.users = users;
        this.messages = messages;
        this.timeStamp = timeStamp;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_image() {
        return chat_image;
    }

    public void setChat_image(String chat_image) {
        this.chat_image = chat_image;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
