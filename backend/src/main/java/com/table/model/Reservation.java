package com.table.model;

import jakarta.persistence.*;
import lombok.*;

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
    private UUID publicId = UUID.randomUUID();
    private LocalDateTime start;
    private int duration;
    private int numberOfCustomers;
    @ManyToOne
    @JoinColumn(name = "customer_privateId", referencedColumnName = "privateId", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "table_privateId", referencedColumnName = "privateId", nullable = false)
    private DiningSpot table;


}
