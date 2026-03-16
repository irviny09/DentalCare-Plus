package com.ubam.dentcare_plus.dto.auth;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    String username;
    String password;
    String name;
    String lastname;
    String celphone;
    Date fechaNacimiento;
}
