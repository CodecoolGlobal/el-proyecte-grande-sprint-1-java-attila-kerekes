package com.table.controller;

import com.table.controller.dto.DiningSpotDTO;
import com.table.controller.dto.NewDiningSpotDTO;
import com.table.controller.dto.RegisterRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.DiningSpot;
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

    @PostMapping("/diningSpot/{restaurant_uuid}")
    public DiningSpot addDiningSpotToRestaurant(@PathVariable  UUID restaurant_uuid, @RequestBody NewDiningSpotDTO newDiningSpotDTO){
        return restaurantService.addDiningSpotToRestaurant(restaurant_uuid, newDiningSpotDTO);
    }

    //Read
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public RestaurantDTO getRestaurantDetail(@PathVariable String email) {
        return restaurantService.getRestaurantByEmail(email);
    }

    @GetMapping("/diningSpot/{email}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public List<DiningSpot> getDiningSpotByEmail(@PathVariable String email){
        return restaurantService.getDiningSpotsByEmail(email);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    List<RestaurantDTO> getRestaurantsByName(@PathVariable String name) {
        return restaurantService.getRestaurantsByName(name);
    }

//    @GetMapping("/{id}/available-tables")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<List<AvailableTableDTO>> getAvailableTables(
//            @PathVariable UUID id,
//            @RequestParam int numberOfGuests) {
//        List<AvailableTableDTO> availableTables = restaurantService.getAvailableTables(id, numberOfGuests);
//        return ResponseEntity.ok(availableTables);
//    }


    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    //Update
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO,
                                          @PathVariable UUID id){
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }


}
