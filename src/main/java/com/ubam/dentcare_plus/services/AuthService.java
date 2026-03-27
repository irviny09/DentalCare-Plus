package com.ubam.dentcare_plus.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.dto.auth.TokenDTO;
import com.ubam.dentcare_plus.entities.Cliente;
import com.ubam.dentcare_plus.entities.Role;
import com.ubam.dentcare_plus.entities.User;
import com.ubam.dentcare_plus.dto.auth.LoginDTO;
import com.ubam.dentcare_plus.dto.auth.RegistroDTO;
import com.ubam.dentcare_plus.jwt.JwtService;
import com.ubam.dentcare_plus.repositories.ClienteRepository;
import com.ubam.dentcare_plus.repositories.RoleRepository;
import com.ubam.dentcare_plus.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public TokenDTO login(LoginDTO request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return TokenDTO.builder()
            .token(token)
            .role(user.getRole().getName())
            .build();
    }

    @Transactional
    public TokenDTO register(RegistroDTO request) {
        Role userRole = roleRepository.findById(3).orElseThrow(() -> new RuntimeException("Error: rol no encontrado!"));
        User user = User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .apellido(request.getLastname())
                        .telefono(request.getCelphone())
                        .activo(true)
                        .role(userRole)
                        .build();
        userRepository.save(user);

        Cliente cliente = Cliente.builder()
                                .user(user)
                                .fechaNacimiento(request.getFechaNacimiento())
                                .saldoPendiente(new BigDecimal(0))
                                .expediente(numExpediente())
                                .build();
        clienteRepository.save(cliente);

        return TokenDTO.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public String numExpediente(){
        long totalPacientes = clienteRepository.count();
        return String.format("EXP-%04d" , totalPacientes + 1);
    }

}
