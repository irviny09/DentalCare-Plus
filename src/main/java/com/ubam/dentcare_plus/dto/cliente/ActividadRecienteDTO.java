package com.ubam.dentcare_plus.dto.cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadRecienteDTO {
    Integer id;
    LocalDate fecha;
    Float costo;
    String nombre;
    String estatus;
}
