package com.ubam.dentcare_plus.UsuarioController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class UserViewController {
    @GetMapping("/dashboard")
    public String dashboard(){
        return "clientes/dashboard";
    }
}
