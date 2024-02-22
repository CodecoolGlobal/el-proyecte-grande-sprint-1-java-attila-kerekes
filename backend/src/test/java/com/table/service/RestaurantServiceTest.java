package com.table.service;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        Restaurant restaurant1 = new Restaurant(new RestaurantDTO("restaurant1", "fakeEmail", "admin", "555-555", "street street 15201"));
        Restaurant restaurant2 = new Restaurant(new RestaurantDTO("restaurant2", "fakeEmail", "admin", "555-555", "street street 15201"));
        when(tempRepositoryMock.getRestaurants()).thenReturn(Set.of(
                restaurant1,
                restaurant2
        ));


        Assertions.assertEquals(2, restaurantService.getRestaurants().size());
        Assertions.assertTrue(restaurantService.getRestaurants().containsAll(List.of(restaurant1, restaurant2)));
    }

    @Test
    void addRestaurant() {
        Restaurant restaurant1 = new Restaurant(new RestaurantDTO("restaurant1", "fakeEmail", "admin", "555-555", "street street 15201"));
        when(tempRepositoryMock.addRestaurant(restaurant1)).thenReturn(true
        );

        Assertions.assertTrue(restaurantService.addRestaurant(restaurant1));
    }

    @Test
    void getRestaurantById() {
    }

    @Test
    void deleteRestaurant() {
    }

    @Test
    void updateRestaurant() {
    }
}