package com.table.controller;

import com.table.controller.dto.JwtResponse;
import com.table.controller.dto.LogInRequestDTO;
import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.security.Role;
import com.table.security.jwt.JwtUtils;
import com.table.service.CustomerService;
import com.table.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public RestaurantDTO addRestaurant(@RequestBody NewRestaurantDTO NewRestaurantDTO) {
        return restaurantService.addRestaurant(NewRestaurantDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LogInRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), Role.ROLE_CUSTOMER));
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
