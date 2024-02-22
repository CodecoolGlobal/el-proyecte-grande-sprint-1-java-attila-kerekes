package com.table.service;

import com.table.model.Restaurant;
import com.table.repository.TempRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {
    @BeforeEach
   public static RestaurantService restaurantService(){
       return new RestaurantService(new TempRepository());
    }

    @Test
    void getRestaurants() {
    }

    @Test
    void addRestaurant() {
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