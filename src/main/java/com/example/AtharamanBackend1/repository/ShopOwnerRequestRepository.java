package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.RequestStatus;
import com.example.AtharamanBackend1.entity.ShopOwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopOwnerRequestRepository extends JpaRepository<ShopOwnerRequest, Long> {
    List<ShopOwnerRequest> findByStatus(RequestStatus status);
}
