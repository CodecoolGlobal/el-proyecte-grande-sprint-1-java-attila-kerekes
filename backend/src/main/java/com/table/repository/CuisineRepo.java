package com.table.repository;

import com.table.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepo extends JpaRepository<Cuisine, Long> {
}
