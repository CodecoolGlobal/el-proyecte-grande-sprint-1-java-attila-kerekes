package com.table.service;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Cuisine;
import com.table.model.Restaurant;
import com.table.model.Table;
import com.table.repository.RestaurantRepo;
import com.table.repository.TableRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private RestaurantRepo restaurantRepo;
    private TableRepo tableRepo;
    private Cuisine cuisineRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, TableRepo tableRepo) {
        this.restaurantRepo = restaurantRepo;
        this.tableRepo = tableRepo;
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
    public Table addTableToRestaurant(UUID restaurantId, Table table, int capacity, String name){
        table.setRestaurant(getRestaurantById(restaurantId));
        table.setCapacity(capacity);
        table.setName(name);
        tableRepo.save(table);
        return table;
    }

    public List<Table> addTablesToRestaurant(UUID restaurantId, List<Table> tables, int capacity, String name){
        Restaurant restaurant = getRestaurantById(restaurantId);
        for (Table table : tables) {
        table.setRestaurant(restaurant);
        table.setCapacity(capacity);
        table.setName(name);
        tableRepo.save(table);
        }
        return tables;
    }


//    public Cuisine addCuisinesToRestaurant(UUID restaurantId, Cuisine cuisine){
//        Restaurant restaurant = getRestaurantById(restaurantId);
//        restaurant.getCuisines().add(cuisine);
//        restaurantRepo.save(restaurant);
//        return cuisine;
//    }



}
