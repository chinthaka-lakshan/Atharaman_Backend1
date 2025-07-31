package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table (name = "HotelOwner")
public class HotelOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "hotel_owner_name")
    private String ownerName;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "contact_number")
    private String contactNumber;

    @Column (name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "hotelOwner")
    private List<Hotel> hotels;

}
