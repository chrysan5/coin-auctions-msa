package com.nameslowly.coinauctions.chat.domain.repository;

import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import com.nameslowly.coinauctions.chat.domain.model.ChatroomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatroomMemberRepository extends JpaRepository<ChatroomMember, Long> {
    List<ChatroomMember> findAllByUsernameAndIsDeleteFalse(String username);

    Optional<ChatroomMember> findByUsernameAndChatroom(String username, Chatroom chatroom);

    ChatroomMember findByChatroom(Chatroom chatroom);

    List<ChatroomMember> findAllByChatroom(Chatroom chatroom);
}

