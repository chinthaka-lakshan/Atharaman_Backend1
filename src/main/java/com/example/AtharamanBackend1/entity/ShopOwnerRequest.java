package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Entity
@Table(name = "shop_owner_request")
public class ShopOwnerRequest {
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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;




}
