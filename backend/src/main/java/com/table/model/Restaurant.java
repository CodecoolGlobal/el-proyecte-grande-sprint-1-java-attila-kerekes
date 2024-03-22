package com.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_gen")
    @SequenceGenerator(name = "restaurant_seq_gen", sequenceName = "restaurant_seq", initialValue = 1, allocationSize = 1)
    private long id;
    //TODO: FIX THIS
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID publicId = UUID.randomUUID();
    private String name;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<DiningSpot> tables;
    @ManyToMany(mappedBy = "restaurants", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cuisine> cuisines;
    @OneToOne(cascade = CascadeType.ALL)
    private Client client;
}
