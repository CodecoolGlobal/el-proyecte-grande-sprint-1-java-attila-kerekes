package com.table.controller.dto;


import java.util.UUID;

public record RestaurantDTO(UUID publicId, String name, String email, String phoneNumber, String address) {
}
