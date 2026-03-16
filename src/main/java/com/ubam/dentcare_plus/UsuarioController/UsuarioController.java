package com.ubam.dentcare_plus.UsuarioController;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/actividad")
    public ResponseEntity<List<ActivityResponse>> showActivity(){
        return ResponseEntity.ok(usuarioService.showActivity());     
    }
    @GetMapping("/citaSiguiente")
    public ResponseEntity<List<CitaSiguienteResponse>> showCitaSiguiente(){
        return ResponseEntity.ok(usuarioService.showCitaSiguiente());
    }
    @GetMapping("/saldoPendiente")
    public ResponseEntity<BigDecimal> showSaldo(){
        return ResponseEntity.ok(usuarioService.showSaldo());
    }
    
}
