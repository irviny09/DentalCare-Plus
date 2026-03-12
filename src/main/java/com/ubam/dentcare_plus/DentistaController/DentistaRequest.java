package com.ubam.dentcare_plus.DentistaController;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DentistaRequest {
    Integer userId;
    Integer dentistaId;
    String diagnostico;
    String tratamiento;
    String recomendacion;
    Date fechaConsulta;
}
