package com.auction.service;

import com.auction.model.Item;
import com.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Autowired
    public DataInitializer(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if database is empty to avoid duplicating data on restarts
        if (itemRepository.count() == 0) {
            
            // Create a sample item that expires 2 hours from the time you boot the server
            Item sampleItem = new Item(
                "Vintage Leather Jacket",
                "A pristine, classic 1970s brown leather aviator jacket. Excellent condition.",
                150.00, // Starting bid price
                LocalDateTime.now().plusHours(2) // Auction active for 2 hours
            );

            // Persist the sample item to the H2 database
            itemRepository.save(sampleItem);
            
            System.out.println(">> Database Seeded: Sample Auction Item Created with ID: " + sampleItem.getId());
        }
    }
}
