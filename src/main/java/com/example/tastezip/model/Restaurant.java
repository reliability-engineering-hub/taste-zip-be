package com.example.tastezip.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(length = 500)
    private String image;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    protected Restaurant(){

    }

    public Restaurant(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public void changeRestaurantInfo(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.updatedAt = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
