package com.ps.service;

import com.ps.model.Chat;
import com.ps.model.Message;
import com.ps.model.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user , Integer chatId, Message req) throws Exception;

    public List<Message> findChatMessages(Integer chatId) throws Exception;

}
