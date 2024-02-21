package com.table.controller;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class RestaurantController {
    private TempRepository tempRepository;

    @Autowired
    public RestaurantController(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    //Create
    @PostMapping("/restaurants")
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        if (tempRepository.addRestaurant(new Restaurant(restaurantDTO))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Read
    @GetMapping("/restaurants")
    public Set<Restaurant> getAllRestaurants() {
        return tempRepository.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return tempRepository.getRestaurant(id);
    }

    //Update
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable UUID id) {
        if (tempRepository.updateRestaurant(restaurantDTO, id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //Delete
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable UUID id) {
        if (tempRepository.deleteRestaurant(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
