package com.table.service;

import com.table.model.Reservation;
import com.table.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final TempRepository tempRepository;

    @Autowired
    public ReservationService(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    public boolean createNewReservation(Reservation reservation) {
        if (reservation == null){
            return false;
        }
        return tempRepository.addReservation(reservation);
    }

    public boolean deleteReservation(Reservation reservation) {
        if (reservation == null){
            return false;
        }
        return tempRepository.deleteReservation(reservation.getID());
    }

    public Collection<Reservation> getAllByRestaurantID(UUID id) {
        List<Reservation> reservationListByRestaurant = new ArrayList<>();
        reservationListByRestaurant.addAll(
                tempRepository.getAllReservation()
                        .stream()
                        .filter(reservation -> reservation.getTable().getRestaurant().getId().equals(id))
                        .toList());

        return reservationListByRestaurant;

    }

    public Collection<Reservation> getAllByCustomerID(UUID id) {
        List<Reservation> reservationListByCustomer = new ArrayList<>();
        reservationListByCustomer.addAll(
                tempRepository.getAllReservation()
                        .stream()

                        .filter(reservation -> reservation.getCustomer().getPublicId().equals(id))
                        .toList());

        return reservationListByCustomer;
    }

}
