package com.nameslowly.coinauctions.chat.application.dto;

import com.nameslowly.coinauctions.chat.domain.model.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto {
    private Long chatMessageId;
    private String senderId; //username
    private String nickname;
    private String message;
    private String sendTime;
    private String type;
    private String imageurl;
    private boolean isDelete;
    private Long chatroomId;

    public ChatMessageDto(ChatMessage chatMessage){
        this.chatMessageId = chatMessage.getChatMessageId();
        this.senderId = chatMessage.getSenderId();
        this.nickname = chatMessage.getNickname();
        this.message = chatMessage.getMessage();
        this.sendTime = chatMessage.getSendTime();
        this.type = chatMessage.getType().toString();
        this.imageurl = chatMessage.getImageurl();
        this.chatroomId = chatMessage.getChatroom().getChatroomId();
    }
}
