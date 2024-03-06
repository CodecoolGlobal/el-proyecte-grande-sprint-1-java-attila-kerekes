package com.table.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    private String cuisineType;
    @ManyToMany
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "cuisine_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )    private List<Restaurant> restaurants;

    public long getPrivateId() {
        return privateId;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
