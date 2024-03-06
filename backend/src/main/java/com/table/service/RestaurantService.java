package com.table.service;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.RestaurantRepo;
import com.table.repository.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RestaurantService {
    private RestaurantRepo restaurantRepo;
    private TableRepo tableRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, TableRepo tableRepo) {
        this.restaurantRepo = restaurantRepo;
        this.tableRepo = tableRepo;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    // TODO: ADD METHOD
    public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPassword(restaurantDTO.password());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        return restaurant;
    }

    public Restaurant getRestaurantById(UUID uuid) {
        return restaurantRepo.findByPublicId(uuid).orElseThrow(NoSuchElementException::new);
    }

    public Restaurant deleteRestaurant(UUID uuid) {
        return restaurantRepo.deleteRestaurantByPublicId(uuid);
    }

    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO, UUID uuid) {
        Restaurant restaurant = getRestaurantById(uuid);
        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPassword(restaurantDTO.password());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        restaurantRepo.save(restaurant);
        return restaurant;
    }

}
