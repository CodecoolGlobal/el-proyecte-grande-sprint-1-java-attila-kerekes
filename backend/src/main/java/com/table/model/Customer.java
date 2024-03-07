package com.table.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuisine_gen")
    @SequenceGenerator(name="cuisine_gen", sequenceName="cuisine_seq")
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId = UUID.randomUUID();
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;


    public Customer(UUID id, String email, String password, String firstName, String lastName, String phoneNumber) {
    }
}
