package com.table.controller.dto;

import java.util.UUID;

public record RestaurantDTO (UUID id, String name, String email, String password, String phoneNumber, String address){
}
