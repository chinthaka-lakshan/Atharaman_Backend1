package com.example.AtharamanBackend1.repository;


import com.example.AtharamanBackend1.entity.HotelOwnerRequest;
import com.example.AtharamanBackend1.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelOwnerRequestRepository extends JpaRepository<HotelOwnerRequest ,Long> {

    List<HotelOwnerRequest> findByStatus(RequestStatus status);
}
