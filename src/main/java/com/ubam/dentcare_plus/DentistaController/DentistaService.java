package com.ubam.dentcare_plus.DentistaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Entities.Historial;
import com.ubam.dentcare_plus.Repositories.ClienteRepository;
import com.ubam.dentcare_plus.Repositories.HistorialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DentistaService {
    @Autowired
    private final HistorialRepository historialRepository;
    @Autowired
    private final ClienteRepository clienteRepository;

    
}
