package com.ps.service;

import com.ps.model.Chat;
import com.ps.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUserChat(Integer userId);

}
