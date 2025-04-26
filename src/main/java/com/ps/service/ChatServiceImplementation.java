package com.ps.service;

import com.ps.model.Chat;
import com.ps.model.User;
import com.ps.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);

        if(isExist != null) return isExist;

        Chat chat = new Chat();

        chat.getUsers().add(reqUser);
        chat.getUsers().add(user2);
        chat.setTimeStamp(LocalDateTime.now());


        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {

        Optional<Chat> res = chatRepository.findById(chatId);
        if(res.isEmpty()){
            throw new Exception("chat nor found with id " + chatId);
        }

        return res.get();
    }

    @Override
    public List<Chat> findUserChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
