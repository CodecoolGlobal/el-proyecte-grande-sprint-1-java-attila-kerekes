package com.table.repository;

import com.table.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByPublicId(UUID uuid);

    List<Restaurant> findAllByNameContainsIgnoreCase(String name);

    @Override
    List<Restaurant> findAll();


    Restaurant deleteRestaurantByPublicId(UUID uuid);

}
