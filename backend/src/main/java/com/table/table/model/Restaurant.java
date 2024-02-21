package com.table.table.model;

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

    public boolean addTable(Table... tables) {
        return this.tables.addAll(List.of(tables));
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
