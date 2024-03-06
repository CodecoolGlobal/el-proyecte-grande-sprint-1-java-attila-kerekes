package com.table.model;

import com.table.controller.dto.RestaurantDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<Table> tables;


    public Restaurant() {
    }

    public UUID getId() {
        return publicId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<Table> getTables() {
        return new ArrayList<>(tables);
    }

    public Restaurant update(RestaurantDTO restaurantDTO) {
        this.name = restaurantDTO.name();
        this.email = restaurantDTO.email();
        this.password = restaurantDTO.password();
        this.phoneNumber = restaurantDTO.phoneNumber();
        this.address = restaurantDTO.address();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(publicId, that.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicId);
    }
}
