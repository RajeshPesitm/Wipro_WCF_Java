package com.auction.controller;

import com.auction.model.Item;
import com.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final ItemRepository itemRepository;

    @Autowired
    public ViewController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String viewAuctionDashboard(Model model) {
        // Fetch our dummy data item (ID: 1) created by the DataInitializer
        Item item = itemRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("No auction items initialized."));
        
        // Pass the item entity to Thymeleaf's view context variable context
        model.addAttribute("item", item);
        
        // Tells Spring to search for and render "src/main/resources/templates/index.html"
        return "index";
    }
}
