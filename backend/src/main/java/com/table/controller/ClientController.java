package com.table.controller;

import com.table.controller.dto.JwtResponse;
import com.table.controller.dto.LogInRequestDTO;
import com.table.exception.UserNotFoundException;
import com.table.security.Role;
import com.table.security.jwt.JwtUtils;
import com.table.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public ClientController(ClientService clientService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LogInRequestDTO loginRequest) throws UserNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(Role.ROLE_RESTAURANT.name())) {
                return new JwtResponse(jwt, userDetails.getUsername(), Role.ROLE_RESTAURANT);
            } else if (authority.getAuthority().equals(Role.ROLE_CUSTOMER.name())) {
                return new JwtResponse(jwt, userDetails.getUsername(), Role.ROLE_CUSTOMER);
            }
        }
        throw new UserNotFoundException("Client not found.");
    }
}
