package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.LogInRequestDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Customer;
import com.table.repository.CustomerRepo;
import com.table.security.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
  private final CustomerRepo repo;


    @Autowired
    public CustomerService(CustomerRepo repo) {
        this.repo = repo;
    }

    public CustomerDTO getCustomerById(UUID id) {
        //TODO: exception message needed (probably everywhere)
        Customer customer = repo.findByPublicId(id).orElseThrow(EntityNotFoundException::new);
        return new CustomerDTO(customer.getPublicId(), customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }

    public UUID findByEmailAndPassword(String email, String password){
        Customer customer = repo.findByEmailEqualsAndPasswordEquals(email, password).orElseThrow(EntityNotFoundException::new);
        return customer.getPublicId();
    }
    public CustomerDTO saveCustomer(NewCustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.email());
        customer.setPassword(customer.getPassword());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        customer.setRole(Role.ROLE_CUSTOMER);
        customer.setPassword(customerDTO.password());
        Customer saved = repo.save(customer);
        return new CustomerDTO(saved.getPublicId(), saved.getEmail(), saved.getFirstName(), saved.getLastName(), saved.getPhoneNumber());
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        repo.deleteByPublicId(id);
    }

    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        System.out.println(customerDTO.id());
        Customer customer = repo.findByPublicId(customerDTO.id()).orElseThrow(EntityNotFoundException::new);;
        customer.setEmail(customerDTO.email());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        repo.save(customer);
        return new CustomerDTO(customer.getPublicId(), customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }
}
