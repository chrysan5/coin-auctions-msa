package com.nameslowly.coinauctions.chat.domain.repository;

import com.nameslowly.coinauctions.chat.domain.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
