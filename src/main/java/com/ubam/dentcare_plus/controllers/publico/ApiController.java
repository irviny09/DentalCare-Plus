package com.ubam.dentcare_plus.controllers.publico;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.dto.publico.ServiciosResponse;
import com.ubam.dentcare_plus.services.PublicService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class ApiController {
    
    private final PublicService publicService;

    @GetMapping("/show-servicios")
    public ResponseEntity<List<ServiciosResponse>> showServicios(){
        return ResponseEntity.ok(publicService.showServicios());
    }

}
