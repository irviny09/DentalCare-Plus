package com.ubam.dentcare_plus.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Entities.Dentista;
import com.ubam.dentcare_plus.Repositories.DentistaRepository;
import com.ubam.dentcare_plus.User.Role;
import com.ubam.dentcare_plus.User.RoleRepository;
import com.ubam.dentcare_plus.User.User;
import com.ubam.dentcare_plus.User.UserRepository;

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
    public AdminResponse registerDentista(AdminRequest request){
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

        return AdminResponse.builder()
                    .message("Dentista Creado con Exito")
                    .build();

                                    
    }

}
