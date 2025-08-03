package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "hotel_name")
    private String hotelName;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "personal_number")
    private String contactNumber;

    @Column (name = "whatsapp_number")
    private String whatsappNumber;

    @Column (name = "nic_number")
    private String nicNumber;

    @Column (name = "locations")
    private List<String> locations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotelOwner_id",referencedColumnName = "id")
    private HotelOwner hotelOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
