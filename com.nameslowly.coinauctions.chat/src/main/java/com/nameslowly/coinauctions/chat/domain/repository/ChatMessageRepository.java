package com.nameslowly.coinauctions.chat.domain.repository;

import com.nameslowly.coinauctions.chat.domain.model.ChatMessage;
import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatroom(Chatroom chatroom);
}
