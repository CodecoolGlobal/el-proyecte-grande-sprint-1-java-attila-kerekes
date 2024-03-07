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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq_gen")
    @SequenceGenerator(name = "reservation_seq_gen", sequenceName = "reservation_seq", initialValue = 1, allocationSize = 1)
    private long id;
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID publicId = UUID.randomUUID();
    private LocalDateTime start;
    private int duration;
    private int numberOfCustomers;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
    private DiningSpot table;
}
