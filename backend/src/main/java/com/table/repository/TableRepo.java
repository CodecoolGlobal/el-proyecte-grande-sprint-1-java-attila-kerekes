package com.table.repository;

import com.table.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TableRepo extends JpaRepository<Table, Long> {
    Table getTableByPublicId(UUID uuid);
    @Override
    List<Table> findAll();
    Table deleteTableByPublicId(UUID uuid);
}
