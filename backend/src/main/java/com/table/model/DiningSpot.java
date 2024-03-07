package com.table.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class DiningSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;
    private int capacity;
    private String name;
    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "restaurant_privateId", referencedColumnName = "privateId", nullable = false)
    private Restaurant restaurant;


}
