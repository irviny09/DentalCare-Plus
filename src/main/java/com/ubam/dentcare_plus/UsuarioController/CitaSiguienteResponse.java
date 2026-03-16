package com.ubam.dentcare_plus.UsuarioController;

import java.sql.Date;
import java.sql.Time;
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
public class CitaSiguienteResponse {
    LocalDate fecha;
    LocalTime hora;
    String doctor;
}
