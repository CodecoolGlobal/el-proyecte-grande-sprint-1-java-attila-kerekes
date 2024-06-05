package com.table.service;

import com.table.controller.dto.*;
import com.table.model.Client;
import com.table.model.Customer;
import com.table.model.DiningSpot;
import com.table.model.Restaurant;
import com.table.repository.DiningSpotRepo;
import com.table.repository.RestaurantRepo;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

  @Mock
  private RestaurantRepo restaurantRepo;

  @Mock
  private DiningSpotRepo diningSpotRepo;

  @Mock
  private PasswordEncoder passwordEncoder;

  private RestaurantService restaurantService;

  private Restaurant commonRestaurant;

  @BeforeEach
  void setUp() {
    this.restaurantService = new RestaurantService(restaurantRepo, diningSpotRepo, passwordEncoder);
    // Arrange
    commonRestaurant = new Restaurant();
    commonRestaurant.setId(1L);
    commonRestaurant.setPublicId(UUID.randomUUID());
    commonRestaurant.setName("Kitchen");
    commonRestaurant.setAddress("Abc Street 1");
    commonRestaurant.setPhoneNumber("1234567890");
    commonRestaurant.setClient(new Client(2L, "kitchen@example.com", "password", Role.ROLE_RESTAURANT));
  }

  @Test
  void getRestaurantById_ValidId_ReturnsRestaurantDTO(){
    // Arrange
    UUID id = commonRestaurant.getPublicId();
    when(restaurantRepo.findByPublicId(commonRestaurant.getPublicId())).thenReturn(Optional.of(commonRestaurant));

    // Act
    RestaurantDTO result = restaurantService.getRestaurantById(id);

    // Assert
    assertNotNull(result);
    assertEquals(id, result.publicId());
    assertEquals(commonRestaurant.getName(), result.name());
    assertEquals(commonRestaurant.getAddress(), result.address());
    assertEquals(commonRestaurant.getPhoneNumber(), result.phoneNumber());
    assertEquals(commonRestaurant.getClient().getEmail(), result.email());
  }

  @Test
  void getCustomerById_InvalidId_ThrowsEntityNotFoundException() {
    // Arrange
    UUID invalidId = UUID.randomUUID();

    // Act and Assert
    assertThrows(EntityNotFoundException.class, () -> {
      restaurantService.getRestaurantById(invalidId);
    });
  }

  @Test
  void createRestaurant_ValidInput_ReturnsRestaurantDTO() {
    // Arrange
    RegisterRestaurantDTO registerRestaurantDTO = new RegisterRestaurantDTO(
      "name",
      "email",
      "password",
      "address",
      "phoneNumber");
    Client client = new Client();
    client.setEmail(registerRestaurantDTO.email());
    client.setRole(Role.ROLE_RESTAURANT);

    Restaurant savedRestaurant = new Restaurant();
    savedRestaurant.setPublicId(UUID.randomUUID());
    savedRestaurant.setName(registerRestaurantDTO.name());
    savedRestaurant.setAddress(registerRestaurantDTO.address());
    savedRestaurant.setPhoneNumber(registerRestaurantDTO.phoneNumber());
    savedRestaurant.setClient(client);

    when(passwordEncoder.encode(registerRestaurantDTO.password())).thenReturn("encodedPassword");
    when(restaurantRepo.save(any(Restaurant.class))).thenReturn(savedRestaurant);

    // Act
    RestaurantDTO result = restaurantService.createRestaurant(registerRestaurantDTO);

    // Assert
    assertNotNull(result);
//    assertEquals(savedRestaurant.getPublicId(), result.publicId());
    assertEquals(registerRestaurantDTO.name(), result.name());
    assertEquals(registerRestaurantDTO.email(), result.email());
    assertEquals(registerRestaurantDTO.phoneNumber(), result.phoneNumber());
    assertEquals(registerRestaurantDTO.address(), result.address());
  }

  @Test
  void createDiningSpotToRestaurant_ValidInput_ReturnsDiningSpot() {
    // Arrange
    UUID restaurantId = UUID.randomUUID();
    NewDiningSpotDTO newDiningSpotDTO = new NewDiningSpotDTO(10, "name");
    Restaurant restaurant = new Restaurant();
    restaurant.setPublicId(restaurantId);

    when(restaurantRepo.findByPublicId(restaurantId)).thenReturn(java.util.Optional.of(restaurant));

    // Act
    DiningSpot result = restaurantService.createDiningSpotToRestaurant(restaurantId, newDiningSpotDTO);

    // Assert
    assertNotNull(result);
    assertEquals(newDiningSpotDTO.name(), result.getName());
    assertEquals(newDiningSpotDTO.capacity(), result.getCapacity());
    assertEquals(restaurantId, result.getRestaurant().getPublicId());
  }
}
