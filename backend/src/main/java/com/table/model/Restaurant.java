package com.table.model;

import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Restaurant {
    private UUID publicId;
    private long privateId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private final List<Table> tables;

    public Restaurant(NewRestaurantDTO newRestaurantDTO, UUID id) {
        this.publicId = id;
        this.name = newRestaurantDTO.name();
        this.email = newRestaurantDTO.email();
        this.password = newRestaurantDTO.password();
        this.phoneNumber = newRestaurantDTO.phoneNumber();
        this.address = newRestaurantDTO.address();
        this.tables = new ArrayList<>();
    }

    public Restaurant(RestaurantDTO restaurantDTO) {
        this.publicId = restaurantDTO.id();
        this.name = restaurantDTO.name();
        this.email = restaurantDTO.email();
        this.password = restaurantDTO.password();
        this.phoneNumber = restaurantDTO.phoneNumber();
        this.address = restaurantDTO.address();
        this.tables = new ArrayList<>();
    }



    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean addTable(Table... tables) {
        return this.tables.addAll(List.of(tables));
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

    public Restaurant update(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.email = restaurant.getEmail();
        this.password = restaurant.getPassword();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.address = restaurant.getAddress();
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
