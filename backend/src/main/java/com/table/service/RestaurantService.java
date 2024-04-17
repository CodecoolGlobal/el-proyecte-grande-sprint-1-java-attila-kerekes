package com.table.service;

import com.table.controller.dto.NewDiningSpotDTO;
import com.table.controller.dto.RegisterRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Client;
import com.table.model.Cuisine;
import com.table.model.DiningSpot;
import com.table.model.Restaurant;
import com.table.repository.DiningSpotRepo;
import com.table.repository.RestaurantRepo;
import com.table.security.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final DiningSpotRepo diningSpotRepo;
    private final PasswordEncoder encoder;
    private Cuisine cuisineRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, DiningSpotRepo tableRepo, PasswordEncoder encoder) {
        this.restaurantRepo = restaurantRepo;
        this.diningSpotRepo = tableRepo;
        this.encoder = encoder;
    }

    public List<RestaurantDTO> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();

        return restaurants.stream().map(restaurant -> new RestaurantDTO(
                restaurant.getPublicId(),
                restaurant.getName(),
                restaurant.getClient().getEmail(),
                restaurant.getPhoneNumber(),
                restaurant.getAddress()
        )).toList();
    }

    public RestaurantDTO getRestaurantById(UUID uuid) {
        Restaurant restaurant = restaurantRepo.findByPublicId(uuid)
          .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new RestaurantDTO(
          restaurant.getPublicId(),
          restaurant.getName(),
          restaurant.getName(),
          restaurant.getPhoneNumber(),
          restaurant.getAddress());
    }

    public List<RestaurantDTO> getRestaurantsByName(String name) {
        List<Restaurant> restaurants = restaurantRepo.findAllByNameContainsIgnoreCase(name);
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurantDTOs.add(
              new RestaurantDTO(
                restaurant.getPublicId(),
                restaurant.getName(),
                restaurant.getName(),
                restaurant.getPhoneNumber(),
                restaurant.getAddress()));
        }
        if (restaurantDTOs.isEmpty()) {
            throw new EntityNotFoundException("No restaurants found with given name.");
        }
        return restaurantDTOs;
    }

    public List<DiningSpot> getDiningSpotsByEmail(String email) {
        return diningSpotRepo.findDiningSpotsByRestaurant_Client_Email(email);
    }

    public RestaurantDTO getRestaurantByEmail(String email) {
        Restaurant restaurant = restaurantRepo.findRestaurantByClient_Email(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new RestaurantDTO(restaurant.getPublicId(), restaurant.getName(), restaurant.getClient().getEmail(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    @Transactional
    public RestaurantDTO createRestaurant(RegisterRestaurantDTO newRestaurantDTO) {
        Client client = new Client();
        client.setEmail(newRestaurantDTO.email());
        client.setPassword(encoder.encode(newRestaurantDTO.password()));
        client.setRole(Role.ROLE_RESTAURANT);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(newRestaurantDTO.name());
        restaurant.setAddress(newRestaurantDTO.address());
        restaurant.setPhoneNumber(newRestaurantDTO.phoneNumber());
        restaurant.setClient(client);
        restaurantRepo.save(restaurant);

        return new RestaurantDTO(
          restaurant.getPublicId(),
          restaurant.getName(),
          client.getEmail(),
          restaurant.getPhoneNumber(),
          restaurant.getAddress());
    }

    @Transactional
    public DiningSpot createDiningSpotToRestaurant(UUID restaurantId, NewDiningSpotDTO newDiningSpotDTO) {
        DiningSpot diningSpot = new DiningSpot();
        Restaurant restaurant = restaurantRepo.findByPublicId(restaurantId)
          .orElseThrow(() -> new EntityNotFoundException("User not found"));

        diningSpot.setRestaurant(restaurant);
        diningSpot.setCapacity(newDiningSpotDTO.capacity());
        diningSpot.setName(newDiningSpotDTO.name());
        diningSpotRepo.save(diningSpot);

        return diningSpot;
    }

    @Transactional
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, UUID uuid) {
        Restaurant restaurant = restaurantRepo.findByPublicId(uuid)
          .orElseThrow(() -> new EntityNotFoundException("User not found"));

        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.getClient().setEmail(restaurantDTO.email());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        restaurantRepo.save(restaurant);

        return new RestaurantDTO(
          restaurant.getPublicId(),
          restaurant.getName(),
          restaurantDTO.email(),
          restaurant.getPhoneNumber(),
          restaurant.getAddress());
    }

    @Transactional
    public void deleteRestaurant(UUID uuid) {
        restaurantRepo.deleteByPublicId(uuid);
    }
}
