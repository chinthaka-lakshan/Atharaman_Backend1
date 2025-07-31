package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "user_name")
    private String userName;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @OneToOne(mappedBy = "user")
    private Guide guide;

    @OneToOne(mappedBy = "user")
    private VehicleOwner vehicleOwner;

    @OneToOne(mappedBy = "user")
    private HotelOwner hotelOwner;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

}
