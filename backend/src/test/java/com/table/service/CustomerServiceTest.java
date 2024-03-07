//package com.table.service;
//
//import com.table.controller.dto.CustomerDTO;
//import com.table.controller.dto.NewCustomerDTO;
//import com.table.model.Customer;
//import com.table.repository.TempRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@Disabled
//class CustomerServiceTest {
//
//    @Mock
//    TempRepository tempRepository;
//    CustomerService customerService;
//
//    @BeforeEach
//    void setUp() {
//        this.customerService = new CustomerService(tempRepository);
//    }
//
//    @Test
//    void getCustomerById() {
//        UUID id = UUID.randomUUID();
//        Customer customer = new Customer(id, "email", "password", "first", "last", "number");
//
//        when(tempRepository.getCustomer(id)).thenReturn(customer);
//
//        Customer result = customerService.getCustomerById(id);
//
//        assertEquals(result, customer);
//    }
//
//    @Test
//    void saveCustomer() {
//        UUID id = UUID.randomUUID();
//        NewCustomerDTO customerDTO = new NewCustomerDTO("email", "password", "first", "last", "number");
//        Customer customer = new Customer(id, "email", "password", "first", "last", "number");
//        CustomerDTO expected = new CustomerDTO(id, "email", "password", "first", "last", "number");
//
//        when(tempRepository.addCustomer(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
//
//        CustomerDTO result = customerService.saveCustomer(customerDTO);
//
//        assertEquals(result, expected);
//    }
//
//
//    @Test
//    void getCustomers() {
//        UUID id = UUID.randomUUID();
//        Customer customer = new Customer(id, "email", "password", "first", "last", "number");
//
//        when(tempRepository.getCustomers()).thenReturn(Set.of(customer));
//
//        Set<Customer> result = customerService.getCustomers();
//
//        assertEquals(result, Set.of(customer));
//    }
//
//    @Test
//    void deleteCustomer() {
//        UUID id = UUID.randomUUID();
//
//        when(tempRepository.deleteCustomer(id)).thenReturn(true);
//
//        boolean result = customerService.deleteCustomer(id);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void updateCustomer() {
//        UUID id = UUID.randomUUID();
//        CustomerDTO customerDTO = new CustomerDTO(id,"email", "password", "first", "last", "number");
//        Customer customer = new Customer(id, "email", "password", "first", "last", "number");
//
//        when(tempRepository.updateCustomer(customer)).thenReturn(customer);
//
//        CustomerDTO result = customerService.updateCustomer(customerDTO);
//
//        assertEquals(result, customerDTO);
//    }
//}