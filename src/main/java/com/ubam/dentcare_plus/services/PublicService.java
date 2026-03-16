package com.ubam.dentcare_plus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Repositories.ServicioRepository;
import com.ubam.dentcare_plus.dto.publico.ServiciosResponse;
import com.ubam.dentcare_plus.entities.Servicios;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicService {
    @Autowired
    private final ServicioRepository servicioRepository;

    public List<ServiciosResponse> showServicios(){
        List<ServiciosResponse> lista = new ArrayList<>();
        try {
            Iterable<Servicios> data = servicioRepository.findAll();
            for(var servicio : data){
                lista.add(new ServiciosResponse(servicio.getId(), servicio.getNombre() , servicio.getDescripcion(), servicio.getPrecio(), servicio.getImagen()
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return lista;
    }


}
