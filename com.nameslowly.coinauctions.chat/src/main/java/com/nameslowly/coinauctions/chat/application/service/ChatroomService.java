package com.nameslowly.coinauctions.chat.application.service;


import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import com.nameslowly.coinauctions.chat.domain.repository.ChatroomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;


    public Chatroom findChatroom(String chatroomId) {
        return chatroomRepository.findById(Long.valueOf(chatroomId)).orElseThrow(
                () -> new RuntimeException("채팅방이 존재하지 않습니다.")
        );
    }

    public List<Chatroom> findChatrooms() {
        return chatroomRepository.findAll();
    }
}
