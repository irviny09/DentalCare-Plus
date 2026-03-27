package com.ubam.dentcare_plus.dto.dentista;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacientesDTO {
    String expediente;
    String paciente;
    String contacto;
    Boolean estado;
}