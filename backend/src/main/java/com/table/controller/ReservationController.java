package com.table.controller;

import com.table.model.Reservation;
import com.table.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{restaurantID}")
    public ResponseEntity<?> getAllReservationByRestaurant(@PathVariable UUID restaurantID) {
        Collection<Reservation> report = reservationService.getAllByRestaurantID(restaurantID);

        return ResponseEntity.ok(report);
    }


    @GetMapping("/{customerID}")
    public ResponseEntity<?> getAllReservationByCustomer(@PathVariable UUID customerID) {
        Collection<Reservation> report = reservationService.getAllByCustomerID(customerID);
        return ResponseEntity.ok(report);
    }

    @PostMapping
    public ResponseEntity<?> createNewReservation(@RequestBody Reservation reservation) {
        if (reservationService.createNewReservation(reservation)) {
            return ResponseEntity.ok().body("Thank you, for your reservation");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReservation(@RequestBody Reservation reservation) {
        if (reservationService.deleteReservation(reservation)) {
            return ResponseEntity.ok().body("Delete completed");
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}
