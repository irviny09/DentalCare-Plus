package com.ubam.dentcare_plus.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ubam.dentcare_plus.jwt.JwtAuthenticationFiler;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFiler jwtAuthenticationFiler;
        private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/**" , "/public/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("Administrador")
                        .requestMatchers("/dentista/**").hasAuthority("Dentista")
                        .anyRequest().authenticated())
                .sessionManagement(sessionManager -> 
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFiler, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
