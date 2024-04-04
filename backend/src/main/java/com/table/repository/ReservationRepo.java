package com.table.repository;

import com.table.model.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Transactional
    void deleteByPublicId(UUID publicId);
  
    List<Reservation> findByDiningSpot_RestaurantPublicId(UUID restaurantId);

    List<Reservation> findByDiningSpot_PublicId(UUID id);


    List<Reservation> findAllByCustomerPublicId(UUID customerId);

    List<Reservation> findAllByCustomer_Client_Email(String email);

    @Transactional
    Reservation findReservationByPublicId(UUID publicId);
}
