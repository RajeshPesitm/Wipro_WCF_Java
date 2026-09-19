package com.auction.service;

import com.auction.model.Bid;
import com.auction.model.Item;
import com.auction.repository.BidRepository;
import com.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuctionService {

    private final ItemRepository itemRepository;
    private final BidRepository bidRepository;

    @Autowired
    public AuctionService(ItemRepository itemRepository, BidRepository bidRepository) {
        this.itemRepository = itemRepository;
        this.bidRepository = bidRepository;
    }

    /**
     * Evaluates an incoming bid and saves it if valid.
     * @return The updated Item state with its new highest price.
     */
    @Transactional
    public Item processIncomingBid(Long itemId, String bidderName, Double bidAmount) {
        // 1. Verify item exists
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Auction item not found."));

        // 2. Rule Check: Is the auction still open?
        if (LocalDateTime.now().isAfter(item.getEndTime())) {
            throw new IllegalStateException("Bidding has closed for this item.");
        }

        // 3. Rule Check: Is the bid higher than the current price?
        if (bidAmount <= item.getCurrentPrice()) {
            throw new IllegalArgumentException("Bid must be strictly higher than the current price.");
        }

        // 4. Update the item's current high price
        item.setCurrentPrice(bidAmount);
        itemRepository.save(item);

        // 5. Log the bid history record
        Bid bid = new Bid(item, bidderName, bidAmount, LocalDateTime.now());
        bidRepository.save(bid);

        return item;
    }
}
