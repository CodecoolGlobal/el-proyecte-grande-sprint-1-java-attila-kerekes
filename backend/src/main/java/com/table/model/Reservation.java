package com.table.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Reservation (UUID id, LocalDateTime start, int duration,
                           int numberOfCustomers,
                           Customer customer,
                           Table table){


}
