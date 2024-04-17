package com.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Min;

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
    private UUID publicId = UUID.randomUUID();
    @Min(1)
    private int capacity;
    private String name;
    @OneToMany(mappedBy = "diningSpot", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;
}
