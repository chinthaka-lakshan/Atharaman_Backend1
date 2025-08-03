package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "shopowner")

public class ShopOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shopOwnerName")
    private String shopOwnerName;

    @Column(name = "shopOwnerNic")
    private String shopOwnerNic;

    @Column(name = "businessMail")
    private String businessMail;

    @Column(name = "contactNumber")
    private String contactNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "shopOwner")
    private List<Shop> shops;



}
