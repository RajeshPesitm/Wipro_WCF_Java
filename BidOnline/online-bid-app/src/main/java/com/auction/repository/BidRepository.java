package com.auction.repository;

import com.auction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    
    // Custom query method: Spring automatically builds the SQL query to find
    // all bids matching a specific item ID, ordered chronologically.
    List<Bid> findByItemIdOrderByBidTimeDesc(Long itemId);
}
