package com.ubam.dentcare_plus.UsuarioController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/actividad")
    public ResponseEntity<List<ActivityResponse>> showActivity(){
        return ResponseEntity.ok(usuarioService.showServicios());     
    }
    
}
