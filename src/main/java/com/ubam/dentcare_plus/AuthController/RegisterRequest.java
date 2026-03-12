package com.ubam.dentcare_plus.AuthController;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String name;
    String lastname;
    String celphone;
    Date fechaNacimiento;
}
