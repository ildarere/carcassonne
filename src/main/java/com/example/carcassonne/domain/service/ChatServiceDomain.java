package com.example.carcassonne.domain.service;

import com.example.carcassonne.data.chat.ChatRepository;
import com.example.carcassonne.domain.model.–°hatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceDomain implements ChatService{
    @Autowired
    ChatRepository chatRepository;
    @Override
    public –°hatMessage save(–°hatMessage message) {
        return chatRepository.save(message);
    }
}
