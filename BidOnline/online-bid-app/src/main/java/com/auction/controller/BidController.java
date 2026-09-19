package com.auction.controller;

import com.auction.model.Item;
import com.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BidController {

    private final AuctionService auctionService;

    @Autowired
    public BidController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    /**
     * Intercepts incoming messages sent to /app/place-bid.
     * Evaluates data and broadcasts updates to everyone subscribed to /topic/bids.
     */
    @MessageMapping("/place-bid")
    @SendTo("/topic/bids")
    public Item handleIncomingBid(BidMessage message) {
        // Bridges the network layer to your validation and database layer
        return auctionService.processIncomingBid(
                message.getItemId(), 
                message.getBidderName(), 
                message.getAmount()
        );
    }

    // --- Helper Transfer Object (DTO) ---
    // A simple container to hold incoming JSON data matching the participant's submission
    public static class BidMessage {
        private Long itemId;
        private String bidderName;
        private Double amount;

        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }

        public String getBidderName() { return bidderName; }
        public void setBidderName(String bidderName) { this.bidderName = bidderName; }

        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
    }
}
