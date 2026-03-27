package com.ubam.dentcare_plus.dto.cliente;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorasOcupadasDTO {
    LocalTime hora;
}
