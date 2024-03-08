package com.table.service;

import com.table.controller.dto.DiningSpotDTO;
import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Cuisine;
import com.table.model.DiningSpot;
import com.table.model.Restaurant;
import com.table.repository.DiningSpotRepo;
import com.table.repository.RestaurantRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final DiningSpotRepo diningSpotRepo;
    private Cuisine cuisineRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, DiningSpotRepo tableRepo) {
        this.restaurantRepo = restaurantRepo;
        this.diningSpotRepo = tableRepo;
    }

    public List<RestaurantDTO> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream().map(restaurant -> new RestaurantDTO(
                restaurant.getPublicId(),
                restaurant.getName(),
                restaurant.getEmail(),
                restaurant.getPhoneNumber(),
                restaurant.getAddress()
        )).toList();
    }

    public RestaurantDTO addRestaurant(NewRestaurantDTO newRestaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(newRestaurantDTO.name());
        restaurant.setAddress(newRestaurantDTO.address());
        restaurant.setEmail(newRestaurantDTO.email());
        restaurant.setPassword(newRestaurantDTO.password());
        restaurant.setPhoneNumber(newRestaurantDTO.phoneNumber());
        restaurantRepo.save(restaurant);
        return new RestaurantDTO(restaurant.getPublicId(), restaurant.getName(), restaurant.getEmail(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    public RestaurantDTO getRestaurantById(UUID uuid) {
        Restaurant restaurant = restaurantRepo.findByPublicId(uuid).orElseThrow(EntityNotFoundException::new);
        return new RestaurantDTO(restaurant.getPublicId(), restaurant.getName(), restaurant.getName(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    public List<RestaurantDTO> getRestaurantsByName(String name) {
        List<Restaurant> restaurants = restaurantRepo.findAllByNameContainsIgnoreCase(name);
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDTOs.add(new RestaurantDTO(restaurant.getPublicId(), restaurant.getName(), restaurant.getName(), restaurant.getPhoneNumber(), restaurant.getAddress()));
        }
        return restaurantDTOs;
    }


    public void deleteRestaurant(UUID uuid) {
        restaurantRepo.deleteByPublicId(uuid);
    }


    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, UUID uuid) {
        Restaurant restaurant = restaurantRepo.findByPublicId(uuid).orElseThrow(EntityNotFoundException::new);
        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        restaurantRepo.save(restaurant);

        return new RestaurantDTO(restaurant.getPublicId(), restaurant.getName(), restaurantDTO.email(), restaurant.getPhoneNumber(), restaurant.getAddress());
    }

    public DiningSpot addTableToRestaurant(UUID restaurantId, DiningSpotDTO diningSpotDTO) {
        DiningSpot diningSpot = new DiningSpot();
        Restaurant restaurant = restaurantRepo.findByPublicId(restaurantId).orElseThrow(EntityNotFoundException::new);
        diningSpot.setRestaurant(restaurant);
        diningSpot.setCapacity(diningSpotDTO.capacity());
        diningSpot.setName(diningSpotDTO.name());
        diningSpotRepo.save(diningSpot);
        return diningSpot;
    }

    public List<DiningSpotDTO> addTablesToRestaurant(UUID restaurantId, List<DiningSpotDTO> diningSpotDTOs) {
        Restaurant restaurant = restaurantRepo.findByPublicId(restaurantId).orElseThrow(EntityNotFoundException::new);
        for (DiningSpotDTO diningSpotDTO : diningSpotDTOs) {
            DiningSpot newDiningSpot = new DiningSpot();
            newDiningSpot.setRestaurant(restaurant);
            newDiningSpot.setCapacity(diningSpotDTO.capacity());
            newDiningSpot.setName(diningSpotDTO.name());
            diningSpotRepo.save(newDiningSpot);
        }
        return diningSpotDTOs;
    }

    public UUID findByEmailAndPassword(String email, String password) {
        Restaurant restaurant = restaurantRepo.findByEmailEqualsAndPasswordEquals(email, password).orElseThrow(EntityNotFoundException::new);
        return restaurant.getPublicId();
    }

    //TODO: CUISINE ***FEATURE***
}
