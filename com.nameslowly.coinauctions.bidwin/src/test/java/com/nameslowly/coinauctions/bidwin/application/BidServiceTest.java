package com.nameslowly.coinauctions.bidwin.application;

import com.nameslowly.coinauctions.bidwin.application.dto.RegisterBidDto;
import com.nameslowly.coinauctions.bidwin.application.service.BidService;
import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepository;
import java.math.BigDecimal;
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
        RegisterBidDto dto = new RegisterBidDto(1L, 2L, 3L, new BigDecimal("4.4234"));

        // when
        Long bidId = bidService.register(dto);

        // then
        Assertions.assertNotNull(bidRepository.findById(bidId));
    }

}