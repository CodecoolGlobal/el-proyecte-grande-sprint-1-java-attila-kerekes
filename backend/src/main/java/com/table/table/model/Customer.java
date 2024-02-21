package com.table.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private final List<Reservation> reservations;
    private String phoneNumber;

    public Customer(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservations = new ArrayList<>();
        this.phoneNumber = phoneNumber;
    }

    public boolean addReservation(Reservation ... reservations) {
        return this.reservations.addAll(List.of(reservations));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
