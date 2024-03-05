package com.table.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   private UUID publicID = UUID.randomUUID();
   private long ID;
   private LocalDateTime start;
   private Duration duration;
   private int numberOfCustomers;
   @ManyToOne
   @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
   private Customer customer;

   @OneToMany
   @JoinColumn(name = "table_id", referencedColumnName = "id")
   private Table table;

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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
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
