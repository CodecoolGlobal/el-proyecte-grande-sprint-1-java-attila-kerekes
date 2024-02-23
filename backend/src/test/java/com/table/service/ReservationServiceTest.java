package com.table.service;

import com.table.controller.dto.CustomerDTO;
import com.table.controller.dto.RestaurantDTO;
import com.table.model.Customer;
import com.table.model.Reservation;
import com.table.model.Restaurant;
import com.table.model.Table;
import com.table.repository.TempRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private TempRepository tempRepository;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(tempRepository);
    }

    @Test
    void createNewReservationWithNullArgument() {

        assertFalse(reservationService.createNewReservation(null));
    }

    @Test
    void deleteNewReservationWithNullArgument() {

        assertFalse(reservationService.deleteReservation(null));
    }

    @Test
    void createNewReservationWithValidArgument() {
        UUID id = UUID.randomUUID();
        CustomerDTO testCustomerDTO = new CustomerDTO(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        Customer testCustomer = new Customer(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        RestaurantDTO testRestaurantDTO = new RestaurantDTO(
                "Üvegtigris",
                "email@email",
                "password",
                "+1111111345",
                "Address");
        Restaurant testRestaurant = new Restaurant(testRestaurantDTO);
        Table testTable = new Table(5, "tablü", testRestaurant);

        Reservation testReservation = new Reservation(
                UUID.randomUUID(),
                1,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer,
                testTable);
        when(tempRepository.addReservation(testReservation)).thenReturn(true);

        assertTrue(reservationService.createNewReservation(testReservation));
        verify(tempRepository, times(1)).addReservation(testReservation);


    }

    @Test
    void getAllByRestaurantIDWithOneRestaurant() {
        UUID id = UUID.randomUUID();
        CustomerDTO testCustomerDTO = new CustomerDTO(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        Customer testCustomer = new Customer(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090"
        );
        RestaurantDTO testRestaurantDTO = new RestaurantDTO(
                "Üvegtigris",
                "email@email",
                "password",
                "+1111111345",
                "Address");
        Restaurant testRestaurant = new Restaurant(testRestaurantDTO);
        Table testTable = new Table(5, "tablü", testRestaurant);

        Reservation testReservationExpected1 = new Reservation(
                UUID.randomUUID(),
                1,
                LocalDateTime.now(),
                Duration.ofHours(2),
                6,
                testCustomer,
                testTable);

        when(tempRepository.getAllReservation())
                .thenReturn(Set.of(testReservationExpected1));

        Collection<Reservation> expected = reservationService.getAllByRestaurantID(testRestaurant.getId());

        assertEquals(expected, List.of(testReservationExpected1));

    }

    @Test
    void getAllByRestaurantIDWithMoreRestaurant() {
        UUID id = UUID.randomUUID();
        CustomerDTO testCustomerDTO = new CustomerDTO(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        Customer testCustomer = new Customer(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        RestaurantDTO testRestaurantDTO = new RestaurantDTO(
                "Üvegtigris",
                "email@email",
                "password",
                "+1111111345",
                "Address");
        Restaurant testRestaurant1 = new Restaurant(testRestaurantDTO);
        Restaurant testRestaurant2 = new Restaurant(testRestaurantDTO);
        Restaurant testRestaurant3 = new Restaurant(testRestaurantDTO);
        Table testTable = new Table(5, "tablü", testRestaurant1);
        Table testTable2 = new Table(5, "tablü", testRestaurant2);
        Table testTable3 = new Table(5, "tablü", testRestaurant3);
        Reservation testReservationBad1 = new Reservation(
                UUID.randomUUID(),
                1,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer,
                testTable2);
        Reservation testReservationBad2 = new Reservation(
                UUID.randomUUID(),
                2,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer,
                testTable3);

        Reservation testReservationExpected1 = new Reservation(
                UUID.randomUUID(),
                3,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer,
                testTable);

        when(tempRepository.getAllReservation())
                .thenReturn(Set.of(testReservationExpected1,
                        testReservationBad1,testReservationBad2));

        Collection<Reservation> expected = reservationService.getAllByRestaurantID(testRestaurant1.getId());

        assertEquals(expected, List.of(testReservationExpected1));

    }


    @Test
    void getAllByCustomerID() {
        UUID id = UUID.randomUUID();
        CustomerDTO testCustomerDTO = new CustomerDTO(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090");
        Customer testCustomer1 = new Customer(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090"
        );
        Customer testCustomer2 = new Customer(
                id,
                "email@email.com",
                "password",
                "FirstName",
                "LastName",
                "+369990090"
        );
        RestaurantDTO testRestaurantDTO = new RestaurantDTO(
                "Üvegtigris",
                "email@email",
                "password",
                "+1111111345",
                "Address");
        Restaurant testRestaurant1 = new Restaurant(testRestaurantDTO);
        Restaurant testRestaurant2 = new Restaurant(testRestaurantDTO);
        Restaurant testRestaurant3 = new Restaurant(testRestaurantDTO);
        Table testTable = new Table(5, "tablü", testRestaurant1);
        Table testTable2 = new Table(5, "tablü", testRestaurant2);
        Table testTable3 = new Table(5, "tablü", testRestaurant3);
        Reservation testReservationBad1 = new Reservation(
                UUID.randomUUID(),
                1,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer2,
                testTable2);
        Reservation testReservationBad2 = new Reservation(
                UUID.randomUUID(),
                2,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer2,
                testTable3);

        Reservation testReservationExpected1 = new Reservation(
                UUID.randomUUID(),
                3,
                LocalDateTime.now(),
                Duration.ofHours(2),
                5,
                testCustomer1,
                testTable);

        when(tempRepository.getAllReservation())
                .thenReturn(Set.of(testReservationExpected1));

        Collection<Reservation> expected = reservationService.getAllByCustomerID(testCustomer1.getPublicId());

        assertEquals(expected, List.of(testReservationExpected1));
    }
}