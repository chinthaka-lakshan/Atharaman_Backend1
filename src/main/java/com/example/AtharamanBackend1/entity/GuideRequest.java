package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guide_request")
public class GuideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column (name = "guide_name")
    private String guideName;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "personal_number")
    private String personalNumber;

    @Column (name = "whatsapp_number")
    private String whatsappNumber;

    @Column (name = "languages")
    private List<String> languages;

    @Column (name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;



}
