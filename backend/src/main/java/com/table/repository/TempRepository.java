package com.table.repository;

import com.table.controller.dto.RestaurantDTO;
import com.table.model.Customer;
import com.table.model.Reservation;
import com.table.model.Restaurant;
import com.table.model.Table;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TempRepository {
    private final Set<Restaurant> restaurants;
    private final Set<Customer> customers;
    private final Set<Reservation> reservations;

    public TempRepository() {
        this.customers = new HashSet<>();
        this.reservations = new HashSet<>();
        this.restaurants = new HashSet<>();
    }

    public boolean addRestaurant(Restaurant restaurant) {
        return restaurants.add(restaurant);
    }

    public boolean addReservation(Reservation reservation) {
        return reservations.add(reservation);
    }

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public Set<Restaurant> getRestaurants() {
        return new HashSet<>(restaurants);
    }

    public Restaurant getRestaurant(UUID id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Customer getCustomer(UUID id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Reservation getReservation(UUID id) {
        return reservations.stream()
                .filter(reservation -> reservation.id().equals(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean deleteRestaurant(UUID id) {
        return restaurants.removeIf(restaurant -> restaurant.getId().equals(id));
    }

    public boolean deleteReservation(UUID id) {
        return reservations.removeIf(reservation -> reservation.id().equals(id));
    }

    public boolean deleteCustomer(UUID id) {
        return customers.removeIf(customer -> customer.getId().equals(id));
    }

    public boolean updateRestaurant(RestaurantDTO restaurantDTO, UUID id) {
        List<Table> tables = getRestaurant(id).getTables();
        Restaurant restaurant = new Restaurant(id, restaurantDTO, tables);
        deleteRestaurant(id);
       return restaurants.add(restaurant);
    }

    public boolean updateCustomer(Customer updatedCustomer) {
        if (deleteCustomer(updatedCustomer.getId())) {
            return addCustomer(updatedCustomer);
        }
        return false;
    }
}
