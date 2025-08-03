package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
