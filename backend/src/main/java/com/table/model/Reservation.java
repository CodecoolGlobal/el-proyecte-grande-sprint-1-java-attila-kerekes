package com.table.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reservation {

    private UUID publicID;
    private long ID;
    private LocalDateTime start;
    private int duration;
    private int numberOfCustomers;
    private Customer customer;
    private Table table;

    public Reservation(UUID publicID, long ID, LocalDateTime start, int duration,
                       int numberOfCustomers, Customer customer, Table table) {
        this.publicID = publicID;
        this.ID = ID;
        this.start = start;
        this.duration = duration;
        this.numberOfCustomers = numberOfCustomers;
        this.customer = customer;
        this.table = table;
    }

    public UUID getPublicID() {
        return publicID;
    }

    public void setPublicID(UUID publicID) {
        this.publicID = publicID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }



}
