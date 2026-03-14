package com.ubam.dentcare_plus.DentistaController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponse {
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
