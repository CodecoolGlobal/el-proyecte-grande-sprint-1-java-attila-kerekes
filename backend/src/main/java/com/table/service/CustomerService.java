package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.model.Customer;
import com.table.repository.TempRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService {
    private final TempRepository repo;

    public CustomerService(TempRepository repo) {
        this.repo = repo;
    }

    public Customer getCustomerById(UUID id) {
        return repo.getCustomer(id);
    }

    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        return repo.addCustomer(customer);
    }

    //TODO delete this
    public Set<Customer> getCustomers() {
        return repo.getCustomers();
    }

    public boolean deleteCustomer(UUID id) {
        return repo.deleteCustomer(id);
    }

    public boolean updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer customer = new Customer(id, customerDTO, getCustomerById(id).getReservations());
        return repo.updateCustomer(customer);
    }
}
