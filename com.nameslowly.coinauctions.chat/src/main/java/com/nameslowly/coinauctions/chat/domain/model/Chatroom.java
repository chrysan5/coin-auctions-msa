package com.nameslowly.coinauctions.chat.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SQLRestriction("is_delete = false")
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long chatroomId;

    @Column(nullable = false, unique = true)
    private String roomname;

    private String auctionEndTime;

    private boolean isDelete = false;

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    /*@OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatroomMember> chatroomMemberList = new ArrayList<>();*/

    public Chatroom(String roomname){
        this.roomname = roomname;
    }

    public Chatroom(String roomname, String auctionEndTime) {
        this.roomname = roomname;
        this.auctionEndTime = auctionEndTime;
    }
}