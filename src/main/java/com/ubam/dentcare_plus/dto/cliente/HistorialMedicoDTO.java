package com.ubam.dentcare_plus.dto.cliente;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialMedicoDTO {
    Integer historialId;
    LocalDateTime fecha;
    String tratamiento;
    String doctor;
    String diagnostico;
    String recomendaciones;
    List<EstudiosDTO> estudios;
}
