package com.table.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationDTO (UUID publicId, LocalDateTime start, int durationInHours, int numberOfCustomers, UUID customerId, UUID restaurantId, UUID diningTableId) {
}
