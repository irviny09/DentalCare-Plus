package com.ubam.dentcare_plus.DentistaController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.Entities.CitaCompletaView;
import com.ubam.dentcare_plus.Entities.Cliente;
import com.ubam.dentcare_plus.Entities.Dentista;
import com.ubam.dentcare_plus.Entities.Historial;
import com.ubam.dentcare_plus.Repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.Repositories.ClienteRepository;
import com.ubam.dentcare_plus.Repositories.DentistaRepository;
import com.ubam.dentcare_plus.Repositories.HistorialRepository;
import com.ubam.dentcare_plus.jwt.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DentistaService {
    @Autowired
    private final HistorialRepository historialRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final DentistaRepository dentistaRepository;
    @Autowired
    private final CitaCompletaRepository citaCompletaRepository;

    @Transactional
    public DentistaResponse addHistorial(DentistaRequest dentistaRequest){
        
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

        return DentistaResponse.builder()
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
    public DentistaResponse updateStatusCita(StatusRequest statusRequest){
        citaCompletaRepository.updateStatusCita(statusRequest.getStatusId(), statusRequest.getCitaid());
        return DentistaResponse.builder()
                        .message("Cita actualizada correctamente")
                        .build();
    }
    
}
