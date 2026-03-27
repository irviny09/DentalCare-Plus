package com.ubam.dentcare_plus.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspecialistasDTO {
    Integer id;
    String name;
}
