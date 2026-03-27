package com.ubam.dentcare_plus.dto.cliente;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaNuevaDTO {
    Integer dentistaId;
    Integer servicioId;
    LocalDate fecha;
    LocalTime hora;
    String notas;
}
