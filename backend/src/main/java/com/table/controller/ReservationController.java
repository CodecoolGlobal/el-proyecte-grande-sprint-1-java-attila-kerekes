package com.table.controller;

import com.table.controller.dto.NewReservationDTO;
import com.table.model.Reservation;
import com.table.model.Restaurant;
import com.table.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public Collection<Reservation> getAllReservation() {
        return reservationService.getAllReservation();
    }

    @GetMapping("/restaurant/{restaurantID}")
    public Collection<Reservation> getAllReservationByRestaurant(@PathVariable UUID restaurantID) {
        return reservationService.getAllByRestaurantID(restaurantID);
    }


    @GetMapping("/customer/{customerID}")
    public ResponseEntity<?> getAllReservationByCustomer(@PathVariable UUID customerID) {
        Collection<Reservation> report = reservationService.getAllByCustomerID(customerID);
        return ResponseEntity.ok(report);
    }

    //TODO Handle resource not found
    @GetMapping("/{reservationId}")
    public Reservation getReservationByPublicId(@PathVariable UUID reservationId) {
        return reservationService.getReservaton(reservationId);
    }

    //TODO Handle resource not found
    @DeleteMapping("/{reservationId}")
    public Reservation deleteReservation(@PathVariable UUID reservationId) {
        return reservationService.deleteReservation(reservationId);
    }

    @PostMapping("/{restaurantID}/{customerID}")
    public ResponseEntity<?> createNewReservation(
            @PathVariable UUID restaurantID,
            @PathVariable UUID customerID,
            @RequestBody NewReservationDTO reservationDTO) {
        Reservation newRestaurant = reservationService
                .createNewReservation(restaurantID, customerID, reservationDTO);
        return ResponseEntity.ok().body("Thank you, for your reservation");

    }



}
