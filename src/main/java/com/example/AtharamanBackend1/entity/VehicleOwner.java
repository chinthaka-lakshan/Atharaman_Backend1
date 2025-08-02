package com.example.AtharamanBackend1.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "VehicleOwner")
public class VehicleOwner {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
