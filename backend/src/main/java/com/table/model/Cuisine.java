package com.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuisine_seq_gen")
    @SequenceGenerator(name = "cuisine_seq_gen", sequenceName = "cuisine_seq", initialValue = 1, allocationSize = 1)
    private long id;
    @NonNull
    private String cuisineType;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "restaurant_cuisine",
            joinColumns = @JoinColumn(name = "cuisine_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )    private List<Restaurant> restaurants;
}
