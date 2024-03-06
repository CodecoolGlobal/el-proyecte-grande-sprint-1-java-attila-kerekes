package com.table.service;

import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RestaurantService {
    private RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    // TODO: ADD METHOD
    public RestaurantDTO addRestaurant(NewRestaurantDTO newRestaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.update(newRestaurantDTO);
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getEmail(), restaurant.getPassword(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    public Restaurant getRestaurantById(UUID uuid) {
        return restaurantRepo.findByPublicId(uuid).orElseThrow(NoSuchElementException::new);
    }

    public Restaurant deleteRestaurant(UUID uuid) {
        return restaurantRepo.deleteRestaurantByPublicId(uuid);
    }

    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.update(restaurantDTO);
        restaurantRepo.save(restaurant);
        return restaurant;
}
