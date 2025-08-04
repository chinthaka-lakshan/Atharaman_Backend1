package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
