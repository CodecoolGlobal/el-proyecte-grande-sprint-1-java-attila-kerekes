package com.table.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public record Reservation (UUID id,
                           LocalDateTime start,
                           Duration duration,
                           int numberOfCustomers,
                           Customer customer,
                           Table table){


}
