package com.ubam.dentcare_plus.DentistaController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.Entities.CitaCompletaView;

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

    @GetMapping("/show-citas")
    public ResponseEntity<List<CitaCompletaView>> showCitas(){
        return ResponseEntity.ok(dentistaService.showCitas());
    }

    @PutMapping("/update-cita")
    public ResponseEntity<DentistaResponse> updateCita(@RequestBody StatusRequest request){
        return ResponseEntity.ok(dentistaService.updateStatusCita(request));
    }
}
