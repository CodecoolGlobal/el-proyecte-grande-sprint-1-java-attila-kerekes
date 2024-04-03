package com.table.controller;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.RegisterCustomerDTO;
import com.table.security.jwt.JwtUtils;
import com.table.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable UUID id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return customerDTO;
    }

    @GetMapping("email/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable String email) {
        CustomerDTO customerDTO = customerService.getCustomerByEmail(email);
        return customerDTO;
    }

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody RegisterCustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }
}
