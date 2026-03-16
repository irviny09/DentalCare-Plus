package com.ubam.dentcare_plus.AuthController;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Entities.Cliente;
import com.ubam.dentcare_plus.Repositories.ClienteRepository;
import com.ubam.dentcare_plus.User.Role;
import com.ubam.dentcare_plus.User.RoleRepository;
import com.ubam.dentcare_plus.User.User;
import com.ubam.dentcare_plus.User.UserRepository;
import com.ubam.dentcare_plus.jwt.JwtService;

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

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
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
                                .build();
        clienteRepository.save(cliente);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
