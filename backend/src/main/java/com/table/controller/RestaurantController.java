package com.table.controller;

import com.table.controller.dto.LogInRequestDTO;
import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/login")
    public UUID logInRestaurant(@RequestBody LogInRequestDTO logInRequestDTO) {
        return restaurantService.findByEmailAndPassword(logInRequestDTO.email(), logInRequestDTO.password());
    }

    //Read
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    List<RestaurantDTO> getRestaurantsByName(@PathVariable String name) {
        return restaurantService.getRestaurantsByName(name);
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    //Update
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable UUID id) {
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

}
