package com.table.model;

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

    public Customer(UUID id, String email, String password, String firstName, String lastName, List<Reservation> reservations, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservations = new ArrayList<>(reservations);
        this.phoneNumber = phoneNumber;
    }

    public boolean addReservation(Reservation ... reservations) {
        return this.reservations.addAll(List.of(reservations));
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public Customer update(Customer updatedCustomer) {
        this.email = updatedCustomer.getEmail();
        this.password = updatedCustomer.getPassword();
        this.firstName = updatedCustomer.getFirstName();
        this.lastName = updatedCustomer.getLastName();
        this.phoneNumber = updatedCustomer.getPhoneNumber();
        return this;
    }
}
