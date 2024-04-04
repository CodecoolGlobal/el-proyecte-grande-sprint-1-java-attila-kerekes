package com.table.controller;

import com.table.controller.dto.NewReservationDTO;
import com.table.controller.dto.ReservationDTO;
import com.table.controller.dto.TimeslotDTO;
import com.table.model.DiningSpot;
import com.table.model.Reservation;
import com.table.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/disabled-timeslots/{restaurantId}/{numberOfGuests}/{currentWeekMonday}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<TimeslotDTO> getDisabledTimeslots(
            @PathVariable UUID restaurantId,
            @PathVariable int numberOfGuests,
            @PathVariable LocalDate currentWeekMonday) {

        LocalDate endOfWeek = currentWeekMonday.plusDays(7);

        List<DiningSpot> suitableTables = reservationService.suitableTables(numberOfGuests, restaurantId);

        List<LocalDateTime> timeslots = suitableTables.stream()
                .flatMap(table -> table.getReservations().stream())
                .map(Reservation::getStart)
                .filter(start -> !start.isBefore(currentWeekMonday.atStartOfDay())
                        && !start.isAfter(endOfWeek.atTime(23, 59, 59)))
                .toList();

        Map<LocalDateTime, Long> timeslotOccurrences = timeslots.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<TimeslotDTO> disabledTimeslots = timeslotOccurrences.entrySet().stream()
                .filter(entry -> entry.getValue() == suitableTables.size())
                .map(entry -> {
                    LocalDateTime timeslot = entry.getKey();
                    int dayOfWeek = timeslot.getDayOfWeek().getValue() - 1;
                    int hourOfDay = timeslot.getHour();
                    return new TimeslotDTO(dayOfWeek, hourOfDay);
                })
                .toList();

        return disabledTimeslots;
    }


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
        return reservationService.createNewReservation(customerID, restaurantID, reservationDTO);
    }

}
