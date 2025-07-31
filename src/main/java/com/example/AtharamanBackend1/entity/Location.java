package com.example.AtharamanBackend1.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "extra_image1")
    private String extraImage1;

    @Column(name = "extra_image2")
    private String extraImage2;

    @Column(name = "extra_image3")
    private String extraImage3;

    @Column(name = "extra_image4")
    private String extraImage4;

    @Column(name = "location")
    private String location;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "province")
    private String province;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Transient
    public String getMainImage(){
        if(id==null || mainImage==null)
            return null;
        return "/locations/"+id+"/"+mainImage;
    }
}
