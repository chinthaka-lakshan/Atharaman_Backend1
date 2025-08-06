package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "vehicle_owner_request")
public class VehicleOwnerRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "vehicle_owner_name")
    private String vehicleOwnerName;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "personal_number")
    private String personalNumber;

    @Column (name ="whatsapp_number")
    private String whatsappNumber;

    @Column (name = "locations")
    private List<String> locations;

    @Column (name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;



}
