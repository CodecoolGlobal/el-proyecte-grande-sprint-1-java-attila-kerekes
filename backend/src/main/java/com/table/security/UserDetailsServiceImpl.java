package com.table.security;


import com.table.model.Client;
import com.table.model.Customer;
import com.table.model.Restaurant;
import com.table.repository.ClientRepository;
import com.table.repository.CustomerRepo;
import com.table.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<Client> targetCustomer = clientRepository.findByEmail(email);

        if (targetCustomer.isPresent()) {

            List<SimpleGrantedAuthority> roles = new ArrayList<>();

            roles.add(new SimpleGrantedAuthority(targetCustomer.get().getRole().name()));

            return new User(targetCustomer.get().getEmail(), targetCustomer.get().getPassword(),
                    roles);
        } else {
            throw new UsernameNotFoundException(email + " not found.");
        }
    }
}
