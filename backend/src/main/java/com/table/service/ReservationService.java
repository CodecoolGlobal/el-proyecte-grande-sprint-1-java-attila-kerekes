package com.table.service;


import com.table.controller.dto.NewReservationDTO;
import com.table.controller.dto.ReservationDTO;
import com.table.model.Customer;
import com.table.model.DiningSpot;
import com.table.model.Reservation;

import com.table.repository.CustomerRepo;
import com.table.repository.DiningSpotRepo;
import com.table.repository.ReservationRepo;

import com.table.repository.RestaurantRepo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

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

    public ReservationDTO createNewReservation(UUID customerID, NewReservationDTO newReservationDTO) {
        List<DiningSpot> allFreeDiningSpot = diningSpotRepo.findAllByCapacity(newReservationDTO.numberOfCustomers());
        if (allFreeDiningSpot.isEmpty()) {
            throw new EntityNotFoundException("No free tables.");
        }
        Optional<Customer> customer = customerRepo.findByPublicId(customerID);
        if(customer.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer.get());
            reservation.setDiningSpot(allFreeDiningSpot.get(0));
            reservation.setDurationInHours(newReservationDTO.duration());
            reservation.setStart(newReservationDTO.start());
            reservation.setNumberOfCustomers(newReservationDTO.numberOfCustomers());
            reservationRepo.save(reservation);
            return convertReservationEntityToDTO(reservation);
        }
        throw new EntityNotFoundException("User not found.");
    }

    @Transactional
    public void deleteReservation(UUID reservationId) {
        reservationRepo.deleteByPublicId(reservationId);
    }

    public ReservationDTO getReservation(UUID reservationId) {
        Reservation reservation = reservationRepo.findReservationByPublicId(reservationId);
        if (reservation == null) {
            throw new EntityNotFoundException("Unable to find reservation");
        }
        return convertReservationEntityToDTO(reservation);
    }

    public Collection<ReservationDTO> getAllReservations() {
        return convertReservationEntityToDTO(reservationRepo.findAll());

    }

    public Collection<ReservationDTO> getAllByRestaurantID(UUID restaurantId) {
        return convertReservationEntityToDTO(reservationRepo.findByDiningSpot_RestaurantPublicId(restaurantId));
    }

    public Collection<ReservationDTO> getAllByCustomerID(UUID id) {
        return convertReservationEntityToDTO(reservationRepo.findAllByCustomerPublicId(id));
    }

    private List<ReservationDTO> convertReservationEntityToDTO(List<Reservation> reservations) {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(convertReservationEntityToDTO(reservation));
        }
        return reservationDTOS;
    }

    private ReservationDTO convertReservationEntityToDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getPublicId(), reservation.getStart(), reservation.getDurationInHours(), reservation.getNumberOfCustomers(), reservation.getCustomer().getPublicId(), reservation.getDiningSpot().getRestaurant().getPublicId(), reservation.getDiningSpot().getPublicId());
    }
}
