package com.table.service;


import com.table.controller.dto.NewReservationDTO;
import com.table.model.Customer;
import com.table.model.DiningSpot;
import com.table.model.Reservation;

import com.table.repository.CustomerRepo;
import com.table.repository.DiningSpotRepo;
import com.table.repository.ReservationRepo;

import com.table.repository.RestaurantRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ReservationService {
    private ReservationRepo reservationRepo;
    private RestaurantRepo restaurantRepo;
    private CustomerRepo customerRepo;
    private DiningSpotRepo diningSpotRepo;


    @Autowired
    public ReservationService(ReservationRepo reservationRepo, RestaurantRepo restaurantRepo, CustomerRepo customerRepo, DiningSpotRepo diningSpotRepo) {
        this.reservationRepo = reservationRepo;
        this.restaurantRepo = restaurantRepo;
        this.customerRepo = customerRepo;
        this.diningSpotRepo = diningSpotRepo;
    }


    public Reservation createNewReservation(UUID restaurantID, UUID customerID, NewReservationDTO reservationDTO) {
        List<DiningSpot> allFreeDiningSpot = diningSpotRepo
                .findAllByCapacity(reservationDTO.numberOfCustomers());
        Customer targetCustomer = customerRepo.findByPublicId(customerID).get();
        Reservation newReservation = new Reservation();
        newReservation.setCustomer(targetCustomer);
        newReservation.setDiningSpot(allFreeDiningSpot.get(0));
        newReservation.setDuration(reservationDTO.duration());
        newReservation.setStart(reservationDTO.start());
        newReservation.setNumberOfCustomers(reservationDTO.numberOfCustomers());

        return reservationRepo.save(newReservation);
    }

    @Transactional
    public Reservation deleteReservation(UUID reservationId) {
        Reservation targetReservation = getReservation(reservationId);
        reservationRepo.deleteByPublicId(reservationId);
        return targetReservation;
    }

    public Reservation getReservation(UUID reservationId) {
        Reservation targetReservation =
                reservationRepo.findReservationByPublicId(reservationId);
        if (targetReservation == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find reservation");
        }
        return targetReservation;
    }

    public Collection<Reservation> getAllReservation() {
        List<Reservation> allReservation = reservationRepo.findAll();
        return allReservation;

    }

    public Collection<Reservation> getAllByRestaurantID(UUID restaurantId) {
        return reservationRepo.findByDiningSpot_RestaurantPublicId(restaurantId);
    }

    public Collection<Reservation> getAllByCustomerID(UUID id) {
        return reservationRepo.findAllByCustomerPublicId(id);
    }


}
