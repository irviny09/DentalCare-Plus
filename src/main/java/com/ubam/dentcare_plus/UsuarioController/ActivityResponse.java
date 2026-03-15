package com.ubam.dentcare_plus.UsuarioController;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    LocalDate fecha;
    BigDecimal costo;
    String nombre;
    String estatus;
}
