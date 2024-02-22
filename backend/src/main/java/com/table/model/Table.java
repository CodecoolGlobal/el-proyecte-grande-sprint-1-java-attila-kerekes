package com.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Table {
    private final UUID id;
    private int capacity;
    private String name;
    private final List<Reservation> reservations;
    private Restaurant restaurant;

    public Table(int capacity, String name, Restaurant restaurant) {
        this.id = UUID.randomUUID();
        this.capacity = capacity;
        this.name = name;
        this.reservations = new ArrayList<>();
        this.restaurant = restaurant;
    }

    public boolean addReservation(Reservation... reservations) {
        return this.reservations.addAll(List.of(reservations));
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
