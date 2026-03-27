package com.ubam.dentcare_plus.dto.dentista;

import java.math.BigDecimal;
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
public class CitaRegistroDTO {
    Integer citaId;
    LocalDate citaFecha;
    LocalTime citaHora;
    BigDecimal citaCostoFinal;
    String citaNotas;
    Integer clienteId;
    String nombreCliente;
    String clienteTelefono;
    LocalDate clienteFechaNacimiento;
    Integer dentistaId;
    String nombreDentista;
    String especialidad;
    String dentistaCedulaProf;
    String servicioNombre;
    String servicioDescripcion;
    String estatusNombre;
}
