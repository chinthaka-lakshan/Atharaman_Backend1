package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
