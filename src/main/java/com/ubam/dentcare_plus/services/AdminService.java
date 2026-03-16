package com.ubam.dentcare_plus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Repositories.DentistaRepository;
import com.ubam.dentcare_plus.Repositories.RoleRepository;
import com.ubam.dentcare_plus.Repositories.UserRepository;
import com.ubam.dentcare_plus.dto.admin.DentistaRegistration;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.entities.Dentista;
import com.ubam.dentcare_plus.entities.Role;
import com.ubam.dentcare_plus.entities.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DentistaRepository dentistaRepository;
    @Autowired
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MessageResponse registerDentista(DentistaRegistration request){
        Role adminRole = roleRepository.findById(2).orElseThrow(() -> new RuntimeException());
        User user = User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .apellido(request.getLastname())
                        .telefono(request.getCelphone())
                        .activo(true)
                        .role(adminRole)
                        .build();
        userRepository.save(user);

        Dentista dentista = Dentista.builder()
                                .user(user)
                                .cedula(request.getCedula())
                                .especialidad(request.getEspecialidad())
                                .build();
        dentistaRepository.save(dentista);

        return MessageResponse.builder()
                    .message("Dentista Creado con Exito")
                    .build();

                                    
    }

}
