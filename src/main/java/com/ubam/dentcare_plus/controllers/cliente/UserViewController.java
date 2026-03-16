package com.ubam.dentcare_plus.controllers.cliente;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubam.dentcare_plus.entities.Cliente;
import com.ubam.dentcare_plus.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class UserViewController {

    private final ClienteRepository clienteRepository;
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Cliente cliente = clienteRepository.findByEmail(username).orElseThrow(() -> new RuntimeException(username));
        System.out.println(username);
        String nombre = cliente.getUser().getName();
        model.addAttribute("nombre", nombre);
        return "clientes/dashboard";
    }

    @GetMapping("/citas")
    public String citas(){
        return "clientes/citas";
    }
}
