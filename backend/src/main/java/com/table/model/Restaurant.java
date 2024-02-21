package com.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Restaurant {
    private final UUID id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private final List<Table> tables;

    public Restaurant(String name, String email, String password, String phoneNumber, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.tables = new ArrayList<>();
    }

    public Restaurant(UUID id, String name, String email, String password, String phoneNumber, String address, List<Table> tables) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.tables = new ArrayList<>(tables);
    }

    public boolean addTable(Table... tables) {
        return this.tables.addAll(List.of(tables));
    }

    public UUID getId() {
        return id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
