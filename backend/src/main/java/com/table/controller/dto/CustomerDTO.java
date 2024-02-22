package com.table.controller.dto;

import java.util.UUID;

public record CustomerDTO(UUID id, String email, String password, String firstName, String lastName, String phoneNumber) {
}
