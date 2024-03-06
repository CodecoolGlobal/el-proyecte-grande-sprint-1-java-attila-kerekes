package com.table.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "restaurant")
    private List<Table> tables;
    @ManyToMany(mappedBy = "restaurant")
    private List<Cuisine> cuisines;

    public long getPrivateId() {
        return privateId;
    }

    public UUID getPublicId() {
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
        return tables;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
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

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    //    public boolean addTable(Table... tables) {
//        return this.tables.addAll(List.of(tables));
//    }

//    public Restaurant update(Restaurant restaurant) {
//        this.name = restaurant.getName();
//        this.email = restaurant.getEmail();
//        this.password = restaurant.getPassword();
//        this.phoneNumber = restaurant.getPhoneNumber();
//        this.address = restaurant.getAddress();
//        return this;
//    }

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
