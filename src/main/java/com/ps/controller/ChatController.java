package com.ps.controller;

import com.ps.model.Chat;
import com.ps.model.User;
import com.ps.request.CreateChatRequest;
import com.ps.service.ChatService;
import com.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat creatChat(@RequestBody CreateChatRequest req,@RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser,user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt);
        return chatService.findUserChat(user.getId());

    }




}
