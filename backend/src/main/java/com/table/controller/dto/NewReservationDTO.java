package com.table.controller.dto;

import java.time.LocalDateTime;

public record NewReservationDTO(LocalDateTime start, int duration, int numberOfCustomers) {
}
