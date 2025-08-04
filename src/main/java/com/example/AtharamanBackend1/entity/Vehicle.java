package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "price_per_day")
    private String pricePerDay;

    @Column(name = "mileage_per_day")
    private String mileagePerDay;

    @Column(name = "description")
    private String description;

    @Column (name = "locations")
    private List<String> locations;

    @Column(name = "with_driver")
    private String withDriver;

    @Column (name = "image")
    @ElementCollection
    private List<String> imagePaths;

    @ManyToOne
    @JoinColumn(name = "vehicleOwner_id",referencedColumnName = "id")
    private VehicleOwner vehicleOwner;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private  User user;

}
