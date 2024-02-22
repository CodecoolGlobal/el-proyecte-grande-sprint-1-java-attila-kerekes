package com.table.controller;

import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import com.table.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Create
    @PostMapping("/restaurants")
    public ResponseEntity<?> addRestaurant(@RequestBody NewRestaurantDTO newRestaurantDTO) {
        RestaurantDTO restaurant = restaurantService.addRestaurant(newRestaurantDTO);
        return ResponseEntity.ok(restaurant);
    }

    //Read
    @GetMapping("/restaurants")
    public Set<Restaurant> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    //Update
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
       RestaurantDTO updatedRestaurant = restaurantService.updateRestaurant(restaurantDTO);
       return ResponseEntity.ok(updatedRestaurant);
    }

    //Delete
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable UUID id) {
        if (restaurantService.deleteRestaurant(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
