package com.table.service;

import com.table.controller.dto.NewRestaurantDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
class RestaurantServiceTest {

    private TempRepository tempRepositoryMock;
    private RestaurantService restaurantService;

    @BeforeEach
    void beforeEach() {
        tempRepositoryMock = mock(TempRepository.class);
        restaurantService = new RestaurantService(tempRepositoryMock);
    }

    @Test
    void getRestaurants() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Restaurant restaurant1 = new Restaurant(new NewRestaurantDTO("restaurant1", "fakeEmail", "admin", "555-555", "street street 15201"), id1);
        Restaurant restaurant2 = new Restaurant(new NewRestaurantDTO("restaurant2", "fakeEmail", "admin", "555-555", "street street 15201"), id2);
        when(tempRepositoryMock.getRestaurants()).thenReturn(Set.of(
                restaurant1,
                restaurant2
        ));

        Assertions.assertEquals(2, restaurantService.getRestaurants().size());
        Assertions.assertTrue(restaurantService.getRestaurants().containsAll(List.of(restaurant1, restaurant2)));
    }

    @Test
    void addRestaurant() {
        UUID id = UUID.randomUUID();

        NewRestaurantDTO newRestaurantDTO = new NewRestaurantDTO("restaurant1", "fakeEmail", "admin", "555-555", "street street 15201");

        Restaurant restaurant1 = new Restaurant(newRestaurantDTO, id);

        RestaurantDTO restaurantDTO = new RestaurantDTO(id, "restaurant1", "fakeEmail", "admin", "555-555", "street street 15201");

        when(tempRepositoryMock.addRestaurant(ArgumentMatchers.any(Restaurant.class))).thenReturn(restaurant1);

        Assertions.assertEquals(restaurantDTO, restaurantService.addRestaurant(newRestaurantDTO));
    }

    @Test
    void getRestaurantById() {
        UUID uuid = UUID.randomUUID();
        Restaurant restaurant1 = new Restaurant(new NewRestaurantDTO("restaurant1", "fakeEmail", "admin", "555-555", "street street 15201"), uuid);
        when(tempRepositoryMock.getRestaurant(uuid)).thenReturn(restaurant1);

        Assertions.assertEquals(restaurant1, restaurantService.getRestaurantById(uuid));
    }

    @Test
    void deleteRestaurant() {
        UUID uuid = UUID.randomUUID();
        when(tempRepositoryMock.deleteRestaurant(uuid)).thenReturn(true);

        Assertions.assertTrue(restaurantService.deleteRestaurant(uuid));
    }

    @Test
    void updateRestaurant() {
        UUID uuid = UUID.randomUUID();
        Restaurant restaurant2 = new Restaurant(new RestaurantDTO(uuid, "restaurant2", "fakeEmail", "admin", "555-555", "street street 15201"));
        RestaurantDTO restaurantDTO = new RestaurantDTO(uuid, "restaurant2", "fakeEmail", "admin", "555-555", "street street 15201");


        when(tempRepositoryMock.updateRestaurant(restaurant2)).thenReturn(restaurant2);

        Assertions.assertEquals(restaurantDTO, restaurantService.updateRestaurant(restaurantDTO));
    }
}