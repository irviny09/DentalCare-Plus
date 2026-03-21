package com.ubam.dentcare_plus.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.dto.cliente.ActividadRecienteDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaNuevaDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaSiguienteDTO;
import com.ubam.dentcare_plus.dto.cliente.EspecialistasDTO;
import com.ubam.dentcare_plus.dto.cliente.EstudiosDTO;
import com.ubam.dentcare_plus.dto.cliente.FechaDisponibilidadDTO;
import com.ubam.dentcare_plus.dto.cliente.HistorialMedicoDTO;
import com.ubam.dentcare_plus.dto.cliente.HorasOcupadasDTO;
import com.ubam.dentcare_plus.dto.cliente.UpdateCitaDTO;
import com.ubam.dentcare_plus.dto.cliente.ServiciosDTO;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.dto.dentista.HistorialDTO;
import com.ubam.dentcare_plus.entities.Citas;
import com.ubam.dentcare_plus.entities.Cliente;
import com.ubam.dentcare_plus.entities.Dentista;
import com.ubam.dentcare_plus.entities.Estatus;
import com.ubam.dentcare_plus.entities.Estudios;
import com.ubam.dentcare_plus.entities.Historial;
import com.ubam.dentcare_plus.entities.Servicios;
import com.ubam.dentcare_plus.repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.repositories.CitaRepository;
import com.ubam.dentcare_plus.repositories.ClienteRepository;
import com.ubam.dentcare_plus.repositories.DentistaRepository;
import com.ubam.dentcare_plus.repositories.EstatusRepository;
import com.ubam.dentcare_plus.repositories.HistorialRepository;
import com.ubam.dentcare_plus.repositories.ServicioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final CitaCompletaRepository citaCompletaRepository;
    private final ClienteRepository clienteRepository;
    private final CitaRepository citaRepository;
    private final DentistaRepository dentistaRepository;
    private final ServicioRepository servicioRepository;
    private final EstatusRepository estatusRepository;
    private final HistorialRepository historialRepository;

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

    public List<HorasOcupadasDTO> getFechasOcupadas(FechaDisponibilidadDTO fechaDisponibilidadDTO){
        List<HorasOcupadasDTO> citas = citaRepository.showOcupado(
            fechaDisponibilidadDTO.getFecha(), 
            fechaDisponibilidadDTO.getDentistaId());
        return citas;
    }

    public List<EspecialistasDTO> getEspecialistas(){
        List<EspecialistasDTO> lista = new ArrayList<>();
        try{
            Iterable<Dentista> data = dentistaRepository.findAll();
            for (var dentista : data) {
                EspecialistasDTO especialistasDTO = EspecialistasDTO.builder()
                                                            .id(dentista.getId())
                                                            .name(dentista.getUser().getName()+ " " + dentista.getUser().getApellido())
                                                            .build();
                lista.add(especialistasDTO);
            }
        }catch(Exception ex){
            throw new RuntimeException("Error" + ex.getMessage());
        }
        return lista;
    }
    public List<ServiciosDTO> getServicios(){
        List<ServiciosDTO> lista = new ArrayList<>();
        try{
            Iterable<Servicios> data = servicioRepository.findAll();
            for (var service : data) {
                ServiciosDTO serviciosDTO = ServiciosDTO.builder()
                                                            .id(service.getId())
                                                            .name(service.getNombre())
                                                            .build();
                lista.add(serviciosDTO);
            }
        }catch(Exception ex){
            throw new RuntimeException("Eror: " + ex.getMessage());
        }
        return lista;
    }

    @Transactional
    public MessageResponse createNewCita(CitaNuevaDTO citaNuevaDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("error cliente"));
        Dentista dentista = dentistaRepository.findById(citaNuevaDTO.getDentistaId()).orElseThrow(() -> new RuntimeException("error dentista"));
        Servicios servicios = servicioRepository.findById(citaNuevaDTO.getServicioId()).orElseThrow(() -> new RuntimeException("error servicio"));
        Estatus estatus = estatusRepository.findById(1).orElseThrow(() -> new RuntimeException("Error estatus"));
        Citas cita = Citas.builder()
                    .cliente(cliente)
                    .dentista(dentista)
                    .servicios(servicios)
                    .estatus(estatus)
                    .citaFecha(citaNuevaDTO.getFecha())
                    .hora(citaNuevaDTO.getHora())
                    .costoFinal(servicios.getPrecio())
                    .notas(citaNuevaDTO.getNotas())
                    .build();
        citaRepository.save(cita);
        return MessageResponse.builder().message("Cita creada con exito").build();
    }

    public List<HistorialMedicoDTO> getHistorialMedico(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Cliente no esta"));
        List<Historial> historial = historialRepository.findHistorialConEstudios(cliente.getId());
        List<HistorialMedicoDTO> lista = new ArrayList<>();
        for (var element : historial) {
            List<EstudiosDTO> estudios = element.getEstudios().stream()
                                            .map(e -> new EstudiosDTO(e.getId(), e.getNombre(), e.getRutaUrl()))
                                            .toList();
            HistorialMedicoDTO hMedicoDTO = HistorialMedicoDTO.builder()
                                            .historialId(element.getId())
                                            .fecha(element.getFechaConsulta())
                                            .tratamiento(element.getTratamiento())
                                            .doctor(element.getDentista().getUser().getName() + " " + element.getDentista().getUser().getApellido())
                                            .diagnostico(element.getDiagnostico())
                                            .recomendaciones(element.getRecomendaciones())
                                            .estudios(estudios)
                                            .build();
            lista.add(hMedicoDTO);
        }
        return lista;
    }

    public MessageResponse updateStatusCita(UpdateCitaDTO idRequest){
        Citas cita = citaRepository.findById(idRequest.getId()).orElseThrow(() -> new RuntimeException("Cita not found"));
        Estatus estatus = estatusRepository.findById(idRequest.getStatus()).orElseThrow(() -> new RuntimeException("Status nod found"));
        cita.setEstatus(estatus);
        citaRepository.save(cita);
        return MessageResponse.builder().message("Cita Actualizada Correctamente").build();
    }

}

