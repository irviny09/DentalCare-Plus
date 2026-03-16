package com.ubam.dentcare_plus.dto.cliente;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaDisponibilidadDTO {
    LocalDate fecha;
    Integer dentistaId;
}
