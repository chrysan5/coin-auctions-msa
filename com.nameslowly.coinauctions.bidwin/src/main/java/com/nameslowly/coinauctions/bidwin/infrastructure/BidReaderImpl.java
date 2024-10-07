package com.nameslowly.coinauctions.bidwin.infrastructure;

import com.nameslowly.coinauctions.bidwin.domain.model.Bid;
import com.nameslowly.coinauctions.bidwin.domain.model.BidStatus;
import com.nameslowly.coinauctions.bidwin.domain.repository.BidRepository;
import com.nameslowly.coinauctions.bidwin.domain.service.BidReader;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BidReaderImpl implements BidReader {

    private final BidRepository bidRepository;

    @Override
    public Optional<Bid> findWinBidByAuctionId(Long auctionID) {
        return bidRepository.findFirstByAuctionIdAndBidStatusOrderByIdDesc(auctionID,
            BidStatus.WIN);
    }

}
