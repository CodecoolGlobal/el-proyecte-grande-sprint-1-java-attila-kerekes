package com.table.repository;

import com.table.model.Customer;
import com.table.model.Restaurant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByEmailEqualsAndPasswordEquals(String email, String password);

    Optional<Restaurant> findByPublicId(UUID uuid);

    List<Restaurant> findAllByNameContainsIgnoreCase(String name);


    Optional<Restaurant> findByEmail(String email);
    @Override
    List<Restaurant> findAll();

    @Transactional
    void deleteByPublicId(UUID uuid);

}
