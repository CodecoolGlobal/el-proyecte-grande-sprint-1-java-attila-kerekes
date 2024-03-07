package com.table.service;

import com.table.controller.dto.RestaurantDTO;
import com.table.controller.dto.DiningSpotDTO;
import com.table.model.Cuisine;
import com.table.model.DiningSpot;
import com.table.model.Restaurant;
import com.table.repository.RestaurantRepo;
import com.table.repository.DiningSpotRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private RestaurantRepo restaurantRepo;
    private DiningSpotRepo diningSpotRepo;
    private Cuisine cuisineRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, DiningSpotRepo tableRepo) {
        this.restaurantRepo = restaurantRepo;
        this.diningSpotRepo = tableRepo;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

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
        return restaurantRepo.findByPublicId(uuid).orElseThrow(EntityNotFoundException::new);
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

//    public List<Table> addTablesToRestaurant(UUID restaurantId, List<Table> tables){
//        Restaurant restaurant = getRestaurantById(restaurantId);
//        restaurant.setTables(tables);
//        restaurantRepo.save(restaurant);
//        return tables;
//    }

    //TODO: TALK ABOUT THIS!
    public DiningSpot addTableToRestaurant(UUID restaurantId, DiningSpot diningSpot, int capacity, String name) {
        diningSpot.setRestaurant(getRestaurantById(restaurantId));
        diningSpot.setCapacity(capacity);
        diningSpot.setName(name);
        diningSpotRepo.save(diningSpot);
        return diningSpot;
    }

    public List<DiningSpotDTO> addTablesToRestaurant(UUID restaurantId, List<DiningSpotDTO> diningSpotDTOs) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        for (DiningSpotDTO diningSpotDTO : diningSpotDTOs) {
            DiningSpot newDiningSpot = new DiningSpot();
            newDiningSpot.setRestaurant(restaurant);
            newDiningSpot.setCapacity(diningSpotDTO.capacity());
            newDiningSpot.setName(diningSpotDTO.name());
            diningSpotRepo.save(newDiningSpot);
        }
        return diningSpotDTOs;
    }


//    public Cuisine addCuisinesToRestaurant(UUID restaurantId, Cuisine cuisine){
//        Restaurant restaurant = getRestaurantById(restaurantId);
//        restaurant.getCuisines().add(cuisine);
//        restaurantRepo.save(restaurant);
//        return cuisine;
//    }


}
