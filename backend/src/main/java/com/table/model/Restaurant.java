package com.table.model;

import jakarta.persistence.*;

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

    public Restaurant() {
    }

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
}
