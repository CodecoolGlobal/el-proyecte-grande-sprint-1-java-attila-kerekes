package com.table.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TempRepository {
/*    private final Set<Restaurant> restaurants;
    private final Set<Customer> customers;
    private final Set<Reservation> reservations;

    public TempRepository() {
        this.customers = new HashSet<>();
        this.reservations = new HashSet<>();
        this.restaurants = new HashSet<>();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }

    public boolean addReservation(Reservation reservation) {
        return reservations.add(reservation);
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
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
                .filter(customer -> customer.getPublicId().equals(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Set<Reservation> getAllReservation() {
        return new HashSet<>(reservations);
    }

    public Reservation getReservation(long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getID()==(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean deleteRestaurant(UUID id) {
        return restaurants.removeIf(restaurant -> restaurant.getId().equals(id));
    }

    public boolean deleteReservation(long id) {
        return reservations.removeIf(reservation -> reservation.getID()==(id));
    }

    public boolean deleteCustomer(UUID id) {
        return customers.removeIf(customer -> customer.getPublicId().equals(id));
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant oldRestaurant = getRestaurant(restaurant.getId());
        oldRestaurant.update(restaurant);

        return getRestaurant(restaurant.getId());
    }

    public Customer updateCustomer(Customer customer) {
        UUID id = customer.getPublicId();
        return customers.stream().filter(customer1 -> customer1.getPublicId().equals(id)).findFirst().get().update(customer);
    }

    //TODO delete this
    public Set<Customer> getCustomers() {
        return customers;
    }*/
}
