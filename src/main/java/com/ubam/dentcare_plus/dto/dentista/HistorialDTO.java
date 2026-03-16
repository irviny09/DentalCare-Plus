package com.ubam.dentcare_plus.dto.dentista;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDTO {
    Integer userId;
    String diagnostico;
    String tratamiento;
    String recomendacion;
}
