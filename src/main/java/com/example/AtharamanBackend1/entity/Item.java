package com.example.AtharamanBackend1.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "discription")
    private String discription;

    @Column(name = "price")
    private Double price;

    @Column(name = "locations")
    private List<String> locations;

    @Column (name = "image")
    @ElementCollection
    private List<String> imagePaths;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;
}
