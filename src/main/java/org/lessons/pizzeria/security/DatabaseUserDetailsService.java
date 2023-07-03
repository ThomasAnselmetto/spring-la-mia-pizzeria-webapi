package org.lessons.pizzeria.security;

import org.lessons.pizzeria.model.User;
import org.lessons.pizzeria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        recupero user da database a partire dalla stringa username
        Optional<User> result = userRepository.findByEmail(username);
        if (result.isPresent()) {
            //costruire userDetail a partire da user
            return new DatabaseUserDetail(result.get());
        } else {
            throw new UsernameNotFoundException("User con email " + username + "non trovato");
        }
    }

}


