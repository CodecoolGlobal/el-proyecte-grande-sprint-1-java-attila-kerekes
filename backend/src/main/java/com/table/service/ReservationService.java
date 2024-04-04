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

import java.time.Duration;
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

    public List<DiningSpot> suitableTables(int numberOfGuests, UUID publicId) {
        List<DiningSpot> suitableTables = diningSpotRepo.findByCapacityGreaterThanEqualAndCapacityLessThanEqualAndRestaurant_PublicId(
                numberOfGuests,
                numberOfGuests + 2,
                publicId);
        return suitableTables;

    }

    public ReservationDTO createNewReservation(UUID customerID, UUID restaurantId, NewReservationDTO newReservationDTO) {
        List<DiningSpot> allFreeDiningSpot = diningSpotRepo.findByCapacityGreaterThanEqualAndCapacityLessThanEqualAndRestaurant_PublicId(
                newReservationDTO.numberOfCustomers(),
                newReservationDTO.numberOfCustomers() + 2,
                restaurantId);
        if (allFreeDiningSpot.isEmpty()) {
            throw new EntityNotFoundException("No free tables.");
        }
        Optional<Customer> customer = customerRepo.findByPublicId(customerID);
        if (customer.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer.get());
            reservation.setDiningSpot(allFreeDiningSpot.get(0));
            reservation.setDuration(Duration.ofNanos(newReservationDTO.duration().getSeconds()));
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

    public Collection<ReservationDTO> getAllByDiningSpot(UUID diningSpotId){
        return convertReservationEntityToDTO(reservationRepo.findByDiningSpot_PublicId(diningSpotId));
    }
    public Collection<ReservationDTO> getAllByCustomerID(UUID id) {
        return convertReservationEntityToDTO(reservationRepo.findAllByCustomerPublicId(id));
    }

    public Collection<ReservationDTO> getAllByCustomerEmail(String email) {
        return convertReservationEntityToDTO(reservationRepo.findAllByCustomer_Client_Email(email));
    }


    private List<ReservationDTO> convertReservationEntityToDTO(List<Reservation> reservations) {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(convertReservationEntityToDTO(reservation));
        }
        return reservationDTOS;
    }

    private ReservationDTO convertReservationEntityToDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getPublicId(), reservation.getStart(), reservation.getDuration(), reservation.getNumberOfCustomers(), reservation.getCustomer().getPublicId(), reservation.getDiningSpot().getRestaurant().getPublicId(), reservation.getDiningSpot().getPublicId());
    }
}
