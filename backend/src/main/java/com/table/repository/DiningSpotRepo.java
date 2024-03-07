package com.table.repository;

import com.table.model.DiningSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiningSpotRepo extends JpaRepository<DiningSpot, Long> {
    DiningSpot getTableByPublicId(UUID uuid);
    @Override
    List<DiningSpot> findAll();
    DiningSpot deleteTableByPublicId(UUID uuid);
}
