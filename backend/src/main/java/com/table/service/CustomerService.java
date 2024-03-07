package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Customer;
import com.table.repository.CustomerRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
 /*  private final CustomerRepo repo;


    @Autowired
    public CustomerService(CustomerRepo repo) {
        this.repo = repo;
    }

    public Customer getCustomerById(UUID id) {
        return repo.findByPublicId(id).orElseThrow(EntityNotFoundException::new);

    }

    //TODO don't send the password to frontend
    public CustomerDTO saveCustomer(NewCustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.email());
        customer.setPassword(customerDTO.password());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        Customer saved = repo.save(customer);
        return new CustomerDTO(saved.getPublicId(), saved.getEmail(), saved.getPassword(), saved.getFirstName(), saved.getLastName(), saved.getPhoneNumber());
    }

    //TODO delete this
    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    public void deleteCustomer(UUID id) {
        Customer customer = repo.findByPublicId(id).orElseThrow(EntityNotFoundException::new);
        repo.delete(customer);
    }

    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = repo.findByPublicId(customerDTO.id()).orElseThrow(EntityNotFoundException::new);;
        customer.setEmail(customerDTO.email());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        repo.save(customer);
        return new CustomerDTO(customer.getPublicId(), customer.getEmail(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }*/
}
