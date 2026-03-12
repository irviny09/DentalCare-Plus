package com.ubam.dentcare_plus.AdminController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest{
    String username;
    String password;
    String name;
    String lastname;
    String celphone;
    String cedula;
    String especialidad;
}
