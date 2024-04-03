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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
    private long id;
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID publicId = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    public Customer(UUID id, String email, String password, String firstName, String lastName, String phoneNumber) {
    }
}
