package com.ubam.dentcare_plus.UsuarioController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Entities.Cliente;
import com.ubam.dentcare_plus.Entities.Dentista;
import com.ubam.dentcare_plus.Entities.Servicios;
import com.ubam.dentcare_plus.PublicController.ServiciosResponse;
import com.ubam.dentcare_plus.Repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.Repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final CitaCompletaRepository citaCompletaRepository;
    @Autowired
    private final ClienteRepository clienteRepository;

    public List<ActivityResponse> showServicios(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no existe"));
        Integer userId = cliente.getId();
        return citaCompletaRepository.getActivity(userId);
    }
}
