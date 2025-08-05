package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "HotelOwner_Request")
public class HotelOwnerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column (name = "hotel_owner_name")
    private String ownerName;

    @Column (name = "hotel_owner_nic")
    private String hotelOwnerNic;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "contact_number")
    private String contactNumber;

    @Column (name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;


}
