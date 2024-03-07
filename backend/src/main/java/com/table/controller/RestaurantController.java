package com.table.controller;

import com.table.controller.dto.NewRestaurantDTO;
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
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Create
    @PostMapping
    public RestaurantDTO addRestaurant(@RequestBody NewRestaurantDTO NewRestaurantDTO) {
        return restaurantService.addRestaurant(NewRestaurantDTO);
    }

    //Read
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{name}")
    List<RestaurantDTO> getRestaurantsByName(@PathVariable String name) {
        return restaurantService.getRestaurantsByName(name);
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    //Update
    @PutMapping("/{id}")
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable UUID id) {
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    public RestaurantDTO deleteRestaurant(@PathVariable UUID id) {
        return restaurantService.deleteRestaurant(id);
    }
}
