package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Client;
import com.table.model.Customer;
import com.table.repository.ClientRepository;
import com.table.repository.CustomerRepo;
import com.table.security.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final PasswordEncoder encoder;
    private final ClientRepository clientRepository;

    @Autowired
    public CustomerService(CustomerRepo repo, PasswordEncoder encoder, ClientRepository clientRepository) {
        this.customerRepo = repo;
        this.encoder = encoder;
        this.clientRepository = clientRepository;
    }

    public CustomerDTO getCustomerById(UUID id) {
        //TODO: exception message needed (probably everywhere)
        Customer customer = customerRepo.findByPublicId(id).orElseThrow(EntityNotFoundException::new);

        return new CustomerDTO(customer.getPublicId(), customer.getClient().getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }


    public CustomerDTO saveCustomer(NewCustomerDTO customerDTO) {

        Client client = new Client();
        client.setEmail(customerDTO.email());
        client.setPassword(encoder.encode(customerDTO.password()));
        client.setRole(Role.ROLE_CUSTOMER);

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        customer.setClient(client);

        Customer saved = customerRepo.save(customer);


        return new CustomerDTO(saved.getPublicId(), client.getEmail(), saved.getFirstName(), saved.getLastName(), saved.getPhoneNumber());
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        customerRepo.deleteByPublicId(id);
    }

    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        System.out.println(customerDTO.id());
        Customer customer = customerRepo.findByPublicId(customerDTO.id()).orElseThrow(EntityNotFoundException::new);
        ;
        customer.getClient().setEmail(customerDTO.email());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setPhoneNumber(customerDTO.phoneNumber());
        customerRepo.save(customer);
        return new CustomerDTO(customer.getPublicId(), customer.getClient().getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber());
    }
}
