package com.nameslowly.coinauctions.bidwin.application;

import com.nameslowly.coinauctions.bidwin.application.dto.request.RegisterBidRequestDto;
import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BidServiceTest {

    @Autowired
    BidService bidService;
    @Autowired
    BidRepository bidRepository;

    @Test
    public void register() throws Exception {
        // given
        RegisterBidRequestDto dto = new RegisterBidRequestDto(1L, 2L, 3L, 4L);

        // when
        Long bidId = bidService.register(dto);

        // then
        Assertions.assertNotNull(bidRepository.findById(bidId));
    }

}