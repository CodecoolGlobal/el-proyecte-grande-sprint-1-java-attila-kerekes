package com.table.controller.dto;


import com.table.security.Role;

public record JwtResponse(String jwt, String userName, Role role){

        };

