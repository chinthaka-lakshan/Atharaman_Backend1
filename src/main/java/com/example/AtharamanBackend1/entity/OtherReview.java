package com.example.AtharamanBackend1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "otherReview")
public class OtherReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "rating")
    private Integer rating;

    @Column (name = "comment")
    private String comment;

    @Column (name = "type")
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
