package com.table.controller;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.LogInRequestDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Customer;
import com.table.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable UUID id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return customerDTO;
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody NewCustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PostMapping("/login")
    public UUID logInCustomer(@RequestBody LogInRequestDTO logInRequestDTO) {
        return customerService.findByEmailAndPassword(logInRequestDTO.email(), logInRequestDTO.password());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }
}
