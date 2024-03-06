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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publicId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "restaurant")
    private List<Table> tables;
    @ManyToMany(mappedBy = "restaurant")
    private List<Cuisine> cuisines;

}
