package com.auction.repository;

import com.auction.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Inherits automatically: save(), findById(), findAll(), deleteById(), etc.
}

