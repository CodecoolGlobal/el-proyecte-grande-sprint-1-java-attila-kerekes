package com.table.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class DiningSpot {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;
    private int capacity;
    private String name;
    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "restaurant_privateId", referencedColumnName = "privateId", nullable = false)
    private Restaurant restaurant;


    public long getPrivateId() {
        return privateId;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
