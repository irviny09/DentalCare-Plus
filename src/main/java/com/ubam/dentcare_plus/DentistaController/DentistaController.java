package com.ubam.dentcare_plus.DentistaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dentista")
public class DentistaController {
    private final DentistaService dentistaService;

    @PostMapping("/add-historial")
    public ResponseEntity<DentistaResponse> addHistorial(@RequestBody DentistaRequest request){
        return ResponseEntity.ok(dentistaService.addHistorial(request));
    }
}
