package com.ubam.dentcare_plus.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DentistaRegistration{
    String username;
    String password;
    String name;
    String lastname;
    String celphone;
    String cedula;
    String especialidad;
}
