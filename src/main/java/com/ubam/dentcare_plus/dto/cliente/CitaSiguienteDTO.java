package com.ubam.dentcare_plus.dto.cliente;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaSiguienteDTO {
    LocalDate fecha;
    LocalTime hora;
    String doctor;
}
