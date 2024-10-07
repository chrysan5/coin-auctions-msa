package com.nameslowly.coinauctions.bidwin.presentation.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBidResponse implements Serializable {

    private Long bidId;
}
