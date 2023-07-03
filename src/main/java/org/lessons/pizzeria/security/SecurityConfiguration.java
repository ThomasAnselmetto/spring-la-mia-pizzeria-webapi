package org.lessons.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//clase di configurazione ed e' il piu' potente di tutti e ha la possibilita' di sovrascrivere la configurazione base di spring boot
@Configuration
public class SecurityConfiguration {
    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    //    questo e' un PasswordEncoder che deduce l'algoritmo di encoding da una stringa nella password stessa
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
//    DaoAuthenticationProvider classe documentata con i suoi metodi
    DaoAuthenticationProvider authenticationProvider() {
//        creo lautenuitcatorprovider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        gli setto il passwordEncoder
        provider.setPasswordEncoder(passwordEncoder());
//        gli setto lo userDetailProvider
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // definisco la catena di filtri
        http.authorizeHttpRequests()
//                .requestMatchers("/ingredienti").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/create").hasAuthority("ADMIN")
                .requestMatchers("/pizzas/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/offerte/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        return http.build();

    }
}
