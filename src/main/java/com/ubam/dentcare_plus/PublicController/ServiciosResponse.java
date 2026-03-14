package com.ubam.dentcare_plus.PublicController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosResponse {
    Integer id;
    String name;
    String descripcion;
    Float precio;
    String imagen;
}
