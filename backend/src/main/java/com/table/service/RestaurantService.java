package com.table.service;

import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class RestaurantService {
    private TempRepository tempRepository;

    @Autowired
    public RestaurantService(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    public Set<Restaurant> getRestaurants() {
        return tempRepository.getRestaurants();
    }

    public RestaurantDTO addRestaurant(NewRestaurantDTO newRestaurantDTO) {
        Restaurant restaurant = tempRepository.addRestaurant(new Restaurant(newRestaurantDTO, UUID.randomUUID()));
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getEmail(), restaurant.getPassword(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    public Restaurant getRestaurantById(UUID uuid) {
        return tempRepository.getRestaurant(uuid);
    }

    public boolean deleteRestaurant(UUID uuid) {
        return tempRepository.deleteRestaurant(uuid);
    }

    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant(restaurantDTO);
        Restaurant updatedRestaurant = tempRepository.updateRestaurant(restaurant);
        return new RestaurantDTO(updatedRestaurant.getId(), updatedRestaurant.getName(), updatedRestaurant.getEmail(), updatedRestaurant.getPassword(), updatedRestaurant.getPhoneNumber(), updatedRestaurant.getAddress());
    }
}
