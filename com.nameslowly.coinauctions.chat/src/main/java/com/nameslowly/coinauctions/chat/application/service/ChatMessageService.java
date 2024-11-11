package com.nameslowly.coinauctions.chat.application.service;


import com.nameslowly.coinauctions.chat.application.dto.ChatMessageDto;
import com.nameslowly.coinauctions.chat.domain.model.ChatMessage;
import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import com.nameslowly.coinauctions.chat.domain.model.MessageType;
import com.nameslowly.coinauctions.chat.domain.repository.ChatMessageRepository;
import com.nameslowly.coinauctions.chat.domain.repository.ChatroomRepository;
import com.nameslowly.coinauctions.chat.presentation.controller.ChatMessageController;
import com.nameslowly.coinauctions.common.exception.GlobalException;
import com.nameslowly.coinauctions.common.response.ResultCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class ChatMessageService {

    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageDto saveChatMessage(ChatMessageDto chatMessageDto) {
        Chatroom chatroom = chatroomRepository.findById(chatMessageDto.getChatroomId()).orElseThrow(
                () -> new GlobalException(ResultCase.CHATROOM_NOT_FOUND)
        );

        ChatMessage chatMessage = chatMessageRepository.save(new ChatMessage(chatMessageDto, chatroom));
        return new ChatMessageDto(chatMessage);
    }

    @Transactional
    public ChatMessageDto saveChatMessage(ChatMessageDto chatMessageDto, String enterMsg) {
        Chatroom chatroom = chatroomRepository.findById(chatMessageDto.getChatroomId()).orElseThrow(
                () -> new RuntimeException("채팅방이 없습니다")
        );

        ChatMessage chatMessage = chatMessageRepository.save(new ChatMessage(chatMessageDto, enterMsg, chatroom));
        return new ChatMessageDto(chatMessage);
    }

    public String getChatMessages(ChatMessageDto message) {
        Chatroom chatroom = chatroomRepository.findById(message.getChatroomId()).orElseThrow(
                () -> new RuntimeException("채팅방이 없습니다")
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatroom(chatroom);

        String beforeMsgs = ""; //채팅방 과거 메시지 기록을 저장할 String
        for(ChatMessage chatMessage : chatMessageList) {
            //시간 포맷 설정
            LocalDateTime time = LocalDateTime.parse(chatMessage.getSendTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = time.format(formatter);

            if (chatMessage.getType() == MessageType.CHAT) {
                beforeMsgs += "[" + chatMessage.getSenderId() + "] " + chatMessage.getMessage() + " (" + formattedDateTime + ")" + "<br>";
            } else { //enter, exit의 경우
                beforeMsgs += chatMessage.getMessage() + " (" + formattedDateTime + ")" + "<br>";
            }
        }

        return beforeMsgs;
    }
}
