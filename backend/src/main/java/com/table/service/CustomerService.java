package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService {
/*    private final TempRepository repo;

    @Autowired
    public CustomerService(TempRepository repo) {
        this.repo = repo;
    }

    public Customer getCustomerById(UUID id) {
        return repo.getCustomer(id);
    }

    public CustomerDTO saveCustomer(NewCustomerDTO customerDTO) {
        UUID id = UUID.randomUUID();
        String email = customerDTO.email();
        String password = customerDTO.password();
        String firstName = customerDTO.firstName();
        String lastName = customerDTO.lastName();
        String phoneNumber = customerDTO.phoneNumber();
        Customer customer = new Customer(id, email, password, firstName, lastName, phoneNumber);
        Customer saved = repo.addCustomer(customer);
        return new CustomerDTO(saved.getPublicId(), saved.getEmail(), saved.getPassword(), saved.getFirstName(), saved.getLastName(), saved.getPhoneNumber());
    }

    //TODO delete this
    public Set<Customer> getCustomers() {
        return repo.getCustomers();
    }

    public boolean deleteCustomer(UUID id) {
        return repo.deleteCustomer(id);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        UUID id = customerDTO.id();
        String email = customerDTO.email();
        String password = customerDTO.password();
        String firstName = customerDTO.firstName();
        String lastName = customerDTO.lastName();
        String phoneNumber = customerDTO.phoneNumber();
        Customer customer = repo.updateCustomer(new Customer(id, email, password, firstName, lastName, phoneNumber));
        return new CustomerDTO(customer.getPublicId(), customer.getEmail(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }*/
}
