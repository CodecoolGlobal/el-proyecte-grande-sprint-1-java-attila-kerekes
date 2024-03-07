package com.table.controller;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
 /*   private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Create
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO RestaurantDTO) {
        Restaurant restaurant = restaurantService.addRestaurant(RestaurantDTO);

        return ResponseEntity.ok(restaurant);
    }

    //Read
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
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

        return ResponseEntity.ok(restaurantService.deleteRestaurant(id));
    }
*/
    //TODO: findByName
}
