package com.table.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long privateId;
    private String cuisineType;
    @ManyToMany
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "cuisine_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )    private List<Restaurant> restaurants;


}
