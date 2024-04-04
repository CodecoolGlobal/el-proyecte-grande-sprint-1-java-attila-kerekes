package com.table.controller;

import com.table.controller.dto.NewReservationDTO;
import com.table.controller.dto.ReservationDTO;
import com.table.model.Reservation;
import com.table.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Collection<ReservationDTO> getAllReservation() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/restaurant/{restaurantID}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public Collection<ReservationDTO> getAllReservationByRestaurant(@PathVariable UUID restaurantID) {
        return reservationService.getAllByRestaurantID(restaurantID);
    }

    @GetMapping("/customer/{email}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Collection<ReservationDTO> getAllReservationByEmail (@PathVariable String email) {
        return reservationService.getAllByCustomerEmail(email);
    }

//    @GetMapping("/customer/{customerID}")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public Collection<ReservationDTO> getAllReservationByCustomer(@PathVariable UUID customerID) {
//        return reservationService.getAllByCustomerID(customerID);
//    }

    @GetMapping("/{reservationId}")
    public ReservationDTO getReservationByPublicId(@PathVariable UUID reservationId) {
        return reservationService.getReservation(reservationId);
    }
    @GetMapping("/diningSpot/{diningSpotId}")
        public Collection<ReservationDTO> getReservationByDiningSpotId(@PathVariable UUID diningSpotId){
        return reservationService.getAllByDiningSpot(diningSpotId);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable UUID reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @PostMapping("/{restaurantID}/{customerID}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ReservationDTO createNewReservation(@PathVariable UUID restaurantID, @PathVariable UUID customerID, @RequestBody NewReservationDTO reservationDTO) {
        return reservationService.createNewReservation(customerID, reservationDTO);
    }

}
