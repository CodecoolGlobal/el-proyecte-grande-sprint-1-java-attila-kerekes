package com.table.controller;

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
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Create
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantService.addRestaurant(new Restaurant(restaurantDTO));
        return ResponseEntity.ok(restaurant);
    }

    //Read
    @GetMapping
    public Set<Restaurant> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable UUID id) {
       Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantDTO, id);
       return ResponseEntity.ok(updatedRestaurant);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable UUID id) {
        if (restaurantService.deleteRestaurant(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
