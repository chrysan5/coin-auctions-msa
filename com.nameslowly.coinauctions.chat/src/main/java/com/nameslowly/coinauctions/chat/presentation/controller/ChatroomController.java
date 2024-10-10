package com.nameslowly.coinauctions.chat.presentation.controller;


import com.nameslowly.coinauctions.chat.application.service.ChatroomService;
import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatroomController {

    private final ChatroomService chatroomService;

    //채팅룸 목록
    @GetMapping("/rooms")
    public String rooms(Model model, @RequestHeader(value = "X-username", required = true) String username) {
        List<Chatroom> chatroomList = chatroomService.findChatrooms();
        model.addAttribute("rooms", chatroomList);
        model.addAttribute("username", username);
        return "/rooms";
    }

    //채팅방 들어가기(상세)
    @GetMapping("/rooms/{chatroomId}")
    public String room(@PathVariable String chatroomId, Model model, @RequestHeader(value = "X-username", required = true) String username) {
        Chatroom room = chatroomService.findChatroom(chatroomId);
        model.addAttribute("room", room);
        model.addAttribute("username", username);
        return "/room-detail";
    }

    //채팅방 나가기
    @GetMapping("/rooms-out")
    public String main(Model model) {
        List<Chatroom> chatroomList = chatroomService.findChatrooms();
        model.addAttribute("rooms", chatroomList);
        return "redirect:/chat/rooms";
    }
}
