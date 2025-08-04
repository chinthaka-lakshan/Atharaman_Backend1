package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @Column (name = "personal_number")
    private String personalNumber;

    @Column (name = "whatsapp_number")
    private String whatsappNumber;

    @Column (name = "languages")
    private List<String> languages;

    @Column (name = "description")
    private String description;

    @Column (name = "locations")
    private List<String> locations;

    @Column (name = "image")
    @ElementCollection
    private List<String> imagePaths;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
