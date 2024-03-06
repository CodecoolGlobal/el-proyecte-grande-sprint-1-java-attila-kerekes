package com.table.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long privateId;
    private  UUID publicId;
    private int capacity;
    private String name;
    @OneToMany
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "restaurant_privateId", referencedColumnName = "privateId", nullable = false)
    private Restaurant restaurant;

    public Table(int capacity, String name, Restaurant restaurant) {
        this.publicId = UUID.randomUUID();
        this.capacity = capacity;
        this.name = name;
        this.reservations = new ArrayList<>();
        this.restaurant = restaurant;
    }

    public Table() {

    }

    public boolean addReservation(Reservation... reservations) {
        return this.reservations.addAll(List.of(reservations));
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Table table = (Table) object;
        return Objects.equals(privateId, table.privateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privateId);
    }
}
