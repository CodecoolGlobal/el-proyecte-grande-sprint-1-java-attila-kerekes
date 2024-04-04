package com.table.repository;

import com.table.model.DiningSpot;
import com.table.model.Reservation;
import com.table.model.Restaurant;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiningSpotRepo extends JpaRepository<DiningSpot, Long> {
    DiningSpot getTableByPublicId(UUID uuid);

    List<DiningSpot> findDiningSpotsByRestaurant_Client_Email(String email);
    DiningSpot deleteTableByPublicId(UUID uuid);

    List<DiningSpot> findAllByCapacity(int capacity);
}
