package com.ubam.dentcare_plus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ubam.dentcare_plus.dto.dentista.CitasCanceladasDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPendientesDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorDiaDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorDiaDetalleDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorMesDTO;
import com.ubam.dentcare_plus.dto.dentista.DiaRequest;
import com.ubam.dentcare_plus.dto.dentista.HistorialDTO;
import com.ubam.dentcare_plus.dto.dentista.NextPacienteDTO;
import com.ubam.dentcare_plus.dto.dentista.PacientesDTO;
import com.ubam.dentcare_plus.dto.common.IdRequest;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.dto.dentista.StatusDTO;
import com.ubam.dentcare_plus.entities.CitaCompletaView;
import com.ubam.dentcare_plus.entities.Cliente;
import com.ubam.dentcare_plus.entities.Dentista;
import com.ubam.dentcare_plus.entities.Historial;
import com.ubam.dentcare_plus.entities.User;
import com.ubam.dentcare_plus.repositories.CitaCompletaRepository;
import com.ubam.dentcare_plus.repositories.CitaRepository;
import com.ubam.dentcare_plus.repositories.ClienteRepository;
import com.ubam.dentcare_plus.repositories.DentistaRepository;
import com.ubam.dentcare_plus.repositories.HistorialRepository;
import com.ubam.dentcare_plus.repositories.UserRepository;
import com.ubam.dentcare_plus.dto.dentista.UpdateClienteDTO;
import com.ubam.dentcare_plus.dto.cliente.HistorialMedicoDTO;
import com.ubam.dentcare_plus.dto.cliente.EstudiosDTO;
import java.util.stream.Collectors;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.ubam.dentcare_plus.entities.Estudios;
import com.ubam.dentcare_plus.repositories.EstudiosRepository;

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
    private final UserRepository userRepository;
    private final EstudiosRepository estudiosRepository;

    @Transactional
    public MessageResponse addHistorial(HistorialDTO dentistaRequest) {

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

        if (dentistaRequest.getArchivos() != null && !dentistaRequest.getArchivos().isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads/estudios/";
            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                for (MultipartFile file : dentistaRequest.getArchivos()) {
                    if (file.isEmpty()) continue;
                    
                    String originalName = file.getOriginalFilename();
                    String extension = "";
                    if (originalName != null && originalName.lastIndexOf(".") > 0) {
                        extension = originalName.substring(originalName.lastIndexOf("."));
                    }
                    String uniqueName = UUID.randomUUID().toString() + extension;
                    
                    Path filePath = uploadPath.resolve(uniqueName);
                    try (InputStream inputStream = file.getInputStream()) {
                        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    
                    Estudios estudio = new Estudios();
                    estudio.setHistorial(historial);
                    estudio.setNombre(originalName);
                    estudio.setTipo(file.getContentType());
                    estudio.setRutaUrl("/uploads/estudios/" + uniqueName);
                    
                    estudiosRepository.save(estudio);
                }
            } catch (Exception e) {
                throw new RuntimeException("No se pudieron guardar las imágenes: " + e.getMessage());
            }
        }

        if (dentistaRequest.getCitaId() != null) {
            citaCompletaRepository.updateStatusCita(3, dentistaRequest.getCitaId());
        }

        return MessageResponse.builder()
                .message("Historial añadido correctamente")
                .build();

    }

    public List<CitaCompletaView> showCitas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Dentista dentista = dentistaRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Dentista no encontrado"));
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
    public MessageResponse updateStatusCita(StatusDTO statusRequest) {
        citaCompletaRepository.updateStatusCita(statusRequest.getStatusId(), statusRequest.getCitaid());
        return MessageResponse.builder()
                .message("Cita actualizada correctamente")
                .build();
    }

    public CitasPorMesDTO getCitas() {
        return citaRepository.getCitasPorMes(gDentista().getId());
    }

    public CitasPorDiaDTO getCitasDay() {
        return citaRepository.getCitasPorDia(gDentista().getId());
    }

    public CitasCanceladasDTO getCitasCanceladas() {
        return citaRepository.getCitasCanceladas(gDentista().getId());
    }

    public CitasPendientesDTO getCitasPendientes() {
        CitasPendientesDTO citasPendientesDTO = citaRepository.getCitasPendientes(gDentista().getId());
        return citasPendientesDTO;
    }

    public List<CitasPorDiaDetalleDTO> getDetailsCita(DiaRequest diaRequest) {
        return citaRepository.getCitasPorDiaDetalle(gDentista().getId(), diaRequest.getFecha());
    }

    public NextPacienteDTO gPaciente() {
        return citaRepository.getNextPaciente(gDentista().getId());
    }

    public Dentista gDentista() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return dentistaRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public List<PacientesDTO> getPacientesAll() {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        List<PacientesDTO> lista = new ArrayList<>();
        for (var cliente : clientes) {
            PacientesDTO paciente = PacientesDTO.builder()
                    .clienteId(cliente.getId())
                    .userId(cliente.getUser().getId())
                    .expediente(cliente.getExpediente())
                    .paciente(cliente.getUser().getName() + " " + cliente.getUser().getApellido())
                    .contacto(cliente.getUser().getTelefono())
                    .estado(cliente.getUser().getActivo())
                    .build();
            lista.add(paciente);
        }
        return lista;
    }

    public PacientesDTO getPacienteById(IdRequest idRequest) {
        Cliente cliente = clienteRepository.findById(idRequest.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PacientesDTO paciente = PacientesDTO.builder()
                .clienteId(cliente.getId())
                .userId(cliente.getUser().getId())
                .expediente(cliente.getExpediente())
                .paciente(cliente.getUser().getName() + " " + cliente.getUser().getApellido())
                .contacto(cliente.getUser().getTelefono())
                .estado(cliente.getUser().getActivo())
                .build();
        return paciente;
    }

    public List<HistorialMedicoDTO> getHistorialesByCliente(Integer clienteId) {
        return historialRepository.findHistorialConEstudios(clienteId).stream().map(h -> HistorialMedicoDTO.builder()
                .historialId(h.getId())
                .fecha(h.getFechaConsulta())
                .tratamiento(h.getTratamiento())
                .doctor(h.getDentista().getUser().getName() + " " + h.getDentista().getUser().getApellido())
                .diagnostico(h.getDiagnostico())
                .recomendaciones(h.getRecomendaciones())
                .estudios(h.getEstudios() != null ? h.getEstudios().stream().map(e -> EstudiosDTO.builder()
                        .estudioId(e.getId())
                        .nombre(e.getNombre())
                        .rutaUrl(e.getRutaUrl())
                        .build()).collect(Collectors.toList()) : new ArrayList<>())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public MessageResponse updateCliente(Integer clienteId, UpdateClienteDTO request) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        User user = cliente.getUser();
        if (request.getNombre() != null)
            user.setName(request.getNombre());
        if (request.getApellido() != null)
            user.setApellido(request.getApellido());
        if (request.getTelefono() != null)
            user.setTelefono(request.getTelefono());
        if (request.getEstado() != null)
            user.setActivo(request.getEstado());

        userRepository.save(user);

        return MessageResponse.builder()
                .message("Cliente actualizado exitosamente")
                .build();
    }
}
