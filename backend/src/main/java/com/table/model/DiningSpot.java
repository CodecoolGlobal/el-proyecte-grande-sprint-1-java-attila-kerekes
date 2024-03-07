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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dining_seq_gen")
    @SequenceGenerator(name = "dining_seq_gen", sequenceName = "dining_seq", initialValue = 1, allocationSize = 1)
    private long id;
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID publicId;
    private int capacity;
    private String name;
    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;
}
