package com.table.controller.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public record NewReservationDTO(LocalDateTime start, Duration duration, int numberOfCustomers) {
}
