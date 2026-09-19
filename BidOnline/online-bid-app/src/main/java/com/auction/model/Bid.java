package com.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Connects many individual bids to a single auction item
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String bidderName;
    private Double amount;
    private LocalDateTime bidTime;

    // --- Constructors ---
    public Bid() {
        // Required default constructor for JPA
    }

    public Bid(Item item, String bidderName, Double amount, LocalDateTime bidTime) {
        this.item = item;
        this.bidderName = bidderName;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }
}

