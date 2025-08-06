package com.example.AtharamanBackend1.repository;

import com.example.AtharamanBackend1.entity.RequestStatus;
import com.example.AtharamanBackend1.entity.VehicleOwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface VehicleOwnerRequestRepository extends JpaRepository<VehicleOwnerRequest, Long> {

    List<VehicleOwnerRequest> findByStatus(RequestStatus status);
}
