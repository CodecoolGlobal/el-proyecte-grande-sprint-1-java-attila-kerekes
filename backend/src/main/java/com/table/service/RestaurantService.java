package com.table.service;

import com.table.controller.dto.RestaurantDTO;
import com.table.controller.dto.DiningSpotDTO;
import com.table.model.Cuisine;
import com.table.model.DiningSpot;
import com.table.model.Restaurant;
import com.table.repository.RestaurantRepo;
import com.table.repository.DiningSpotRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
/*    private TempRepository tempRepository;
=======
    private RestaurantRepo restaurantRepo;
    private DiningSpotRepo diningSpotRepo;
    private Cuisine cuisineRepo;
>>>>>>> development

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo, DiningSpotRepo tableRepo) {
        this.restaurantRepo = restaurantRepo;
        this.diningSpotRepo = tableRepo;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPassword(restaurantDTO.password());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        return restaurant;
    }

    public Restaurant getRestaurantById(UUID uuid) {
        return restaurantRepo.findByPublicId(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public Restaurant deleteRestaurant(UUID uuid) {
        return restaurantRepo.deleteRestaurantByPublicId(uuid);
    }

<<<<<<< HEAD
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant(restaurantDTO);
        Restaurant updatedRestaurant = tempRepository.updateRestaurant(restaurant);
        return new RestaurantDTO(updatedRestaurant.getId(), updatedRestaurant.getName(), updatedRestaurant.getEmail(), updatedRestaurant.getPassword(), updatedRestaurant.getPhoneNumber(), updatedRestaurant.getAddress());
    }*/

/*    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO, UUID uuid) {
        Restaurant restaurant = getRestaurantById(uuid);
        restaurant.setName(restaurantDTO.name());
        restaurant.setAddress(restaurantDTO.address());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPassword(restaurantDTO.password());
        restaurant.setPhoneNumber(restaurantDTO.phoneNumber());
        restaurantRepo.save(restaurant);
        return restaurant;
    }


    //TODO: TALK ABOUT THIS!
    public DiningSpot addTableToRestaurant(UUID restaurantId, DiningSpotDTO diningSpotDTO) {
        DiningSpot diningSpot = new DiningSpot();
        diningSpot.setRestaurant(getRestaurantById(restaurantId));
        diningSpot.setCapacity(diningSpotDTO.capacity());
        diningSpot.setName(diningSpotDTO.name());
        diningSpotRepo.save(diningSpot);
        return diningSpot;
    }

    public List<DiningSpotDTO> addTablesToRestaurant(UUID restaurantId, List<DiningSpotDTO> diningSpotDTOs) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        for (DiningSpotDTO diningSpotDTO : diningSpotDTOs) {
            DiningSpot newDiningSpot = new DiningSpot();
            newDiningSpot.setRestaurant(restaurant);
            newDiningSpot.setCapacity(diningSpotDTO.capacity());
            newDiningSpot.setName(diningSpotDTO.name());
            diningSpotRepo.save(newDiningSpot);
        }
        return diningSpotDTOs;
    }*/

}
