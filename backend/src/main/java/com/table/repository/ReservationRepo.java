package com.table.repository;

import com.table.model.DiningSpot;
import com.table.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> getReservationsByTable_PublicId(UUID uuid);
}
