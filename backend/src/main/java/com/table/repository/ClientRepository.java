package com.table.repository;

import com.table.model.Client;
import com.table.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    Optional<Client> findByEmailEqualsAndPasswordEquals(String email, String password);

    Optional<Client> findByEmail(String email);
}
