package com.nameslowly.coinauctions.chat.domain.repository;

import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
}

