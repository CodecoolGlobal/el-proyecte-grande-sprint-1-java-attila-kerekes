package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.RegisterCustomerDTO;
import com.table.model.Client;
import com.table.model.Customer;
import com.table.repository.CustomerRepo;
import com.table.security.Role;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepo customerRepo;

  @Mock
  private PasswordEncoder passwordEncoder;

  CustomerService customerService;

  private Customer commonCustomer;

  @BeforeEach
  void setUp() {
    this.customerService = new CustomerService(customerRepo, passwordEncoder);

    // Arrange
    commonCustomer = new Customer();
    commonCustomer.setId(1L);
    commonCustomer.setPublicId(UUID.randomUUID());
    commonCustomer.setFirstName("John");
    commonCustomer.setLastName("Doe");
    commonCustomer.setPhoneNumber("1234567890");
    commonCustomer.setClient(new Client(2L, "john.doe@example.com", "password", Role.ROLE_CUSTOMER));
  }


  @Test
  void getCustomerById_ValidId_ReturnsCustomerDTO() {
    // Arrange
    UUID id = commonCustomer.getPublicId();
    when(customerRepo.findByPublicId(commonCustomer.getPublicId())).thenReturn(Optional.of(commonCustomer));

    // Act
    CustomerDTO result = customerService.getCustomerById(id);

    // Assert
    assertNotNull(result);
    assertEquals(id, result.id());
    assertEquals(commonCustomer.getFirstName(), result.firstName());
    assertEquals(commonCustomer.getLastName(), result.lastName());
    assertEquals(commonCustomer.getPhoneNumber(), result.phoneNumber());
    assertEquals(commonCustomer.getClient().getEmail(), result.email());
  }


  @Test
  void getCustomerById_InvalidId_ThrowsEntityNotFoundException() {
    // Arrange
    UUID invalidId = UUID.randomUUID();

    // Act and Assert
    assertThrows(EntityNotFoundException.class, () -> {
      customerService.getCustomerById(invalidId);
    });
  }


  @Test
  void getCustomerByEmail_ValidEmail_ReturnsCustomerDTO() {
    // Arrange
    String email = commonCustomer.getClient().getEmail();
    when(customerRepo.findCustomerByClient_Email(email)).thenReturn(Optional.of(commonCustomer));

    // Act
    CustomerDTO result = customerService.getCustomerByEmail(email);

    assertNotNull(result);
    assertEquals(commonCustomer.getFirstName(), result.firstName());
    assertEquals(commonCustomer.getLastName(), result.lastName());
    assertEquals(commonCustomer.getPhoneNumber(), result.phoneNumber());
    assertEquals(commonCustomer.getPublicId(), result.id());
  }


  @Test
  void getCustomerByEmail_InvalidEmail_ThrowsEntityNotFoundException() {
    String invalidEmail = "invalid@example.com";

    assertThrows(EntityNotFoundException.class, () -> customerService.getCustomerByEmail(invalidEmail));
  }


  @Test
  void createCustomer_ValidInput_ReturnsCorrectCustomerDTO() {
    // Arrange
    RegisterCustomerDTO customerDTO = new RegisterCustomerDTO(
      "test@example.com",
      "password",
      "John",
      "Doe",
      "123456789");
    Client savedClient = new Client();
    savedClient.setEmail(customerDTO.email());
    savedClient.setRole(Role.ROLE_CUSTOMER);

    Customer savedCustomer = new Customer();
    savedCustomer.setPublicId(UUID.randomUUID());
    savedCustomer.setFirstName(customerDTO.firstName());
    savedCustomer.setLastName(customerDTO.lastName());
    savedCustomer.setPhoneNumber(customerDTO.phoneNumber());
    savedCustomer.setClient(savedClient);

    when(passwordEncoder.encode(customerDTO.password())).thenReturn("encodedPassword");
    when(customerRepo.save(any(Customer.class))).thenReturn(savedCustomer);

    // Act
    CustomerDTO result = customerService.createCustomer(customerDTO);

    // Assert
    assertNotNull(result);
    assertEquals(savedCustomer.getPublicId(), result.id());
    assertEquals(savedClient.getEmail(), result.email());
    assertEquals(savedCustomer.getFirstName(), result.firstName());
    assertEquals(savedCustomer.getLastName(), result.lastName());
    assertEquals(savedCustomer.getPhoneNumber(), result.phoneNumber());

    // Verify that the encoder was called with the correct password
    verify(passwordEncoder).encode(customerDTO.password());

    // Verify that the save method was called with the correct customer
    verify(customerRepo).save(argThat(customer ->
      customer.getFirstName().equals(customerDTO.firstName()) &&
        customer.getLastName().equals(customerDTO.lastName()) &&
        customer.getPhoneNumber().equals(customerDTO.phoneNumber()) &&
        customer.getClient().getEmail().equals(customerDTO.email()) &&
        customer.getClient().getRole() == Role.ROLE_CUSTOMER
    ));
  }


//  @Test
//  void updateCustomer_ValidInput_ReturnsUpdatedCustomerDTO() {
//    // Arrange
//    UUID customerId = UUID.randomUUID();
//    CustomerDTO customerDTO = new CustomerDTO(
//      customerId,
//      "test@example.com",
//      "John",
//      "Doe",
//      "123456789");
//    Customer existingCustomer = new Customer();
//    existingCustomer.setPublicId(customerId);
//    existingCustomer.setFirstName("OldFirstName");
//    existingCustomer.setLastName("OldLastName");
//    existingCustomer.setPhoneNumber("987654321");
//    existingCustomer.setClient(new Client());
//
//    when(customerRepo.findByPublicId(customerId)).thenReturn(Optional.of(existingCustomer));
//    when(customerRepo.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//    // Act
//    CustomerDTO result = customerService.updateCustomer(customerDTO);
//
//    // Assert
//    assertNotNull(result);
//    assertEquals(customerId, result.id());
//    assertEquals(customerDTO.email(), result.email());
//    assertEquals(customerDTO.firstName(), result.firstName());
//    assertEquals(customerDTO.lastName(), result.lastName());
//    assertEquals(customerDTO.phoneNumber(), result.phoneNumber());
//
//    // Verify that the save method was called with the correct customer
//    verify(customerRepo).save(argThat(customer ->
//      customer.getPublicId().equals(customerId) &&
//        customer.getFirstName().equals(customerDTO.firstName()) &&
//        customer.getLastName().equals(customerDTO.lastName()) &&
//        customer.getPhoneNumber().equals(customerDTO.phoneNumber()) &&
//        customer.getClient().getEmail().equals(customerDTO.email()) &&
//        customer.getClient().getRole() == Role.ROLE_CUSTOMER
//    ));
//  }


  @Test
  void deleteCustomer_ValidId_DeletesCustomer() {
    // Arrange
    UUID customerId = UUID.randomUUID();

    // Act
    customerService.deleteCustomer(customerId);

    // Verify that the deleteByPublicId method was called with the correct ID
    verify(customerRepo).deleteByPublicId(customerId);
  }


}