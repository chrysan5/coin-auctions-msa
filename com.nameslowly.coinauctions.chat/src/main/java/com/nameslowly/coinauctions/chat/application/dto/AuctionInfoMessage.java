package com.nameslowly.coinauctions.chat.application.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuctionInfoMessage {
    private String title;
    private String endTime;
    private String registerMemberUsername;
}
