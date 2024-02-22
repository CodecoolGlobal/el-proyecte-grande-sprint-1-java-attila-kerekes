package com.table.service;

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

    public Restaurant addRestaurant(Restaurant restaurant){
        return tempRepository.addRestaurant(restaurant);
    }

    public Restaurant getRestaurantById(UUID uuid){
        return tempRepository.getRestaurant(uuid);
    }

    public boolean deleteRestaurant(UUID uuid){
        return tempRepository.deleteRestaurant(uuid);
    }

    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO, UUID uuid){
    return tempRepository.updateRestaurant(restaurantDTO, uuid);
    }
}
