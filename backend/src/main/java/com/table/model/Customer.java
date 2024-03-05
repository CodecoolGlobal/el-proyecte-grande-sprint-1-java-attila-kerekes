package com.table.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    private long id;
    private final UUID publicId = UUID.randomUUID();
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "customer")
    private final List<Reservation> reservations = new ArrayList<>();
    private String phoneNumber;

    public boolean addReservation(Reservation ... reservations) {
        return this.reservations.addAll(List.of(reservations));
    }

    public UUID getPublicId() {
        return publicId;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer update(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phoneNumber = customer.getPhoneNumber();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(publicId, customer.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicId);
    }
}
