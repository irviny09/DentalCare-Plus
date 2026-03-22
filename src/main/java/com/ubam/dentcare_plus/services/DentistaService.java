package com.ubam.dentcare_plus.services;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.dto.dentista.CitasCanceladasDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPendientesDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorDiaDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorMesDTO;
import com.ubam.dentcare_plus.dto.dentista.HistorialDTO;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.dto.dentista.StatusDTO;
import com.ubam.dentcare_plus.entities.CitaCompletaView;
import com.ubam.dentcare_plus.entities.Cliente;
import com.ubam.dentcare_plus.entities.Dentista;
import com.ubam.dentcare_plus.entities.Historial;
import com.ubam.dentcare_plus.repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.repositories.CitaRepository;
import com.ubam.dentcare_plus.repositories.ClienteRepository;
import com.ubam.dentcare_plus.repositories.DentistaRepository;
import com.ubam.dentcare_plus.repositories.HistorialRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DentistaService {
    private final HistorialRepository historialRepository;
    private final ClienteRepository clienteRepository;
    private final DentistaRepository dentistaRepository;
    private final CitaCompletaRepository citaCompletaRepository;
    private final CitaRepository citaRepository;

    @Transactional
    public MessageResponse addHistorial(HistorialDTO dentistaRequest){
        
        Cliente cliente = clienteRepository.findById(dentistaRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
        Historial historial = Historial.builder()
                                    .cliente(cliente)
                                    .dentista(dentista)
                                    .diagnostico(dentistaRequest.getDiagnostico())
                                    .recomendaciones(dentistaRequest.getRecomendacion())
                                    .tratamiento(dentistaRequest.getTratamiento())
                                    .build();
        historialRepository.save(historial);

        return MessageResponse.builder()
                        .message("Historial añadido correctamente")
                        .build();
        
    }

    public List<CitaCompletaView> showCitas(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
        Integer rolId = dentista.getUser().getRole().getId();
        Integer userId = dentista.getUser().getId();
        System.out.println(userId + rolId);
        List<CitaCompletaView> citas = citaCompletaRepository.getCitas(userId, rolId);
        if (citas.isEmpty()) {
            throw new RuntimeException("No hay citas disponibles");
        }
        return citas;
    }

    @Transactional
    public MessageResponse updateStatusCita(StatusDTO statusRequest){
        citaCompletaRepository.updateStatusCita(statusRequest.getStatusId(), statusRequest.getCitaid());
        return MessageResponse.builder()
                        .message("Cita actualizada correctamente")
                        .build();
    }

    public CitasPorMesDTO getCitas(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Dentista not found"));
        return citaRepository.getCitasPorMes(dentista.getId());
    }

    public CitasPorDiaDTO getCitasDay(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Dentista not found"));
        return citaRepository.getCitasPorDia(dentista.getId());
    }

    public CitasCanceladasDTO getCitasCanceladas(){
        String usernmae = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(usernmae).orElseThrow(() -> new RuntimeException("Dentista not found"));
        return citaRepository.getCitasCanceladas(dentista.getId());
    }

    public CitasPendientesDTO getCitasPendientes(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Dentista not found"));
        return citaRepository.getCitasPendientes(dentista.getId());
    }
    
}
