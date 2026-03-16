package com.ubam.dentcare_plus.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.Repositories.ClienteRepository;
import com.ubam.dentcare_plus.dto.cliente.ActividadRecienteDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaSiguienteDTO;
import com.ubam.dentcare_plus.entities.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final CitaCompletaRepository citaCompletaRepository;
    @Autowired
    private final ClienteRepository clienteRepository;

    public List<ActividadRecienteDTO> showActivity(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no existe"));
        Integer userId = cliente.getId();
        return citaCompletaRepository.getActivity(userId);
    }
    public List<CitaSiguienteDTO> showCitaSiguiente(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("error"));
        return citaCompletaRepository.getCitaSiguiente(cliente.getId());
    }
    public BigDecimal showSaldo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return cliente.getSaldoPendiente();
    }
}
