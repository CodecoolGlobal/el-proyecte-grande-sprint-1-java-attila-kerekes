package com.table.controller;

import com.table.controller.dto.RegisterRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.security.jwt.JwtUtils;
import com.table.service.CustomerService;
import com.table.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final CustomerService customerService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(CustomerService customerService, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, RestaurantService restaurantService) {
        this.customerService = customerService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.restaurantService = restaurantService;
    }

    //Create
    @PostMapping
    public RestaurantDTO addRestaurant(@RequestBody RegisterRestaurantDTO NewRestaurantDTO) {
        return restaurantService.addRestaurant(NewRestaurantDTO);
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
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable UUID id){
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }
}
