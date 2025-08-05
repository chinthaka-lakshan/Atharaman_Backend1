package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "ReviewLocationHotel")
public class ReviewLocationHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "rating")
    private Integer rating;

    @Column (name = "comment")
    private String comment;

    @Column (name = "type")
    private String type;

    @Column(name = "image")
    @ElementCollection
    private List<String> imagePaths;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
