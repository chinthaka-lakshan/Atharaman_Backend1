package com.example.AtharamanBackend1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AtharamanBackend1.entity.Shop;
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
