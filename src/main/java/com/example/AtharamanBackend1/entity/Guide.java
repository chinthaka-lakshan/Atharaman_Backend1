package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Guide")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "guide_name")
    private String guideName;

    @Column (name = "business_mail")
    private String businessMail;

    @Column (name = "personal_nnmber")
    private String personalNomber;

    @Column (name = "whatsapp_number")
    private String whatsappNumber;

    @Column (name = "languages")
    private String languages;

}
