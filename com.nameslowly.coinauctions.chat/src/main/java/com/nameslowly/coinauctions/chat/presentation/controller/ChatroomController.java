package com.nameslowly.coinauctions.chat.presentation.controller;


import com.nameslowly.coinauctions.chat.application.service.ChatroomService;
import com.nameslowly.coinauctions.chat.domain.model.Chatroom;
import com.nameslowly.coinauctions.chat.infrastructure.user.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatroomController {

    private final ChatroomService chatroomService;
    private final AuthService authService;

    //채팅방 목록 -> 일단 유저서비스 켜서 feign으로 user 가져와서 이거 테스트 되는지 보자.
    @GetMapping("/rooms-list")
    public String getChatroomList(Model model, @RequestHeader(value = "X-username", required = true) String username) {
        model.addAttribute("username", username);

        //모든 채팅방 목록
        List<Chatroom> chatroomList = chatroomService.getChatroomList();
        model.addAttribute("rooms", chatroomList);

        //내가 속한 채팅방 목록
        List<Chatroom> myChatroomList = chatroomService.getMyChatroomList(username);
        model.addAttribute("myrooms", myChatroomList);

        return "/rooms";
    }
    
    //채팅방 들어가기(상세)
    @GetMapping("/rooms/{chatroomId}")
    public String enterChatroom(@PathVariable String chatroomId, Model model, @RequestHeader(value = "X-username", required = true) String username) {
        //chatroomId 메세징시스템에서 가져와야함
        Chatroom chatroom = chatroomService.enterChatroom(chatroomId, username);
        model.addAttribute("room", chatroom);
        model.addAttribute("username", username);
        return "/room-detail";
    }

    //채팅방 나가기 -> 채팅방을 나가도 채팅방을 삭제하지 않으면 과거 채팅 기록 조회 가능함
    @GetMapping("/rooms-out")
    public String exitChatroom(Model model) {
        List<Chatroom> chatroomList = chatroomService.getChatroomList();
        model.addAttribute("rooms", chatroomList);
        return "redirect:/chat/rooms-list";
    }

    //채팅방 생성
    @PostMapping("/rooms")
    public String createChatroom(@RequestParam("roomname") String roomname, @RequestHeader(value = "X-username", required = true) String username){
        chatroomService.createChatroom(roomname, username);
        return "redirect:/chat/rooms-list";
    }

    //채팅방 삭제 -> 채팅방 목록에서 보이지 않으므로 과거 채팅 기록 조회 불가능
    @PutMapping("/rooms/{chatroomId}")
    public String deleteChatroom(@PathVariable String chatroomId){
        chatroomService.deleteChatMember(chatroomId);
        return "redirect:/chat/rooms-list";
    }
}
