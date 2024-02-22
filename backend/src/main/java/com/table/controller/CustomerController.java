package com.table.controller;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.NewCustomerDTO;
import com.table.model.Customer;
import com.table.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO delete this
    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody NewCustomerDTO customerDTO) {
        CustomerDTO newCustomer = customerService.saveCustomer(customerDTO);
        return ResponseEntity.ok(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(updated);
    }
}
