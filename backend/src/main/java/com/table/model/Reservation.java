package com.table.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicID = UUID.randomUUID();
    private LocalDateTime start;
    private Duration duration;
    private int numberOfCustomers;
    @ManyToOne
    @JoinColumn(name = "customer_privateId", referencedColumnName = "privateId", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "table_privateId", referencedColumnName = "privateId", nullable = false)
    private DiningSpot table;


    public UUID getPublicID() {
        return publicID;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DiningSpot getTable() {
        return table;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public void setPublicID(UUID publicID) {
        this.publicID = publicID;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTable(DiningSpot table) {
        this.table = table;
    }
}
