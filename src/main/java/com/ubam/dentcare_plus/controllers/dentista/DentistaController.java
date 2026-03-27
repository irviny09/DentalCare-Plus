package com.ubam.dentcare_plus.controllers.dentista;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.dto.dentista.CitasPorDiaDetalleDTO;
import com.ubam.dentcare_plus.dto.dentista.CitasPorMesDTO;
import com.ubam.dentcare_plus.dto.dentista.DiaRequest;
import com.ubam.dentcare_plus.dto.dentista.HistorialDTO;
import com.ubam.dentcare_plus.dto.dentista.PacientesDTO;
import com.ubam.dentcare_plus.dto.common.IdRequest;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.dto.dentista.StatusDTO;
import com.ubam.dentcare_plus.entities.CitaCompletaView;
import com.ubam.dentcare_plus.services.DentistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dentista")
public class DentistaController {
    private final DentistaService dentistaService;

    @PostMapping("/add-historial")
    public ResponseEntity<MessageResponse> addHistorial(@RequestBody HistorialDTO request){
        return ResponseEntity.ok(dentistaService.addHistorial(request));
    }

    @GetMapping("/show-citas")
    public ResponseEntity<List<CitaCompletaView>> showCitas(){
        return ResponseEntity.ok(dentistaService.showCitas());
    }

    @PutMapping("/update-cita")
    public ResponseEntity<MessageResponse> updateCita(@RequestBody StatusDTO request){
        return ResponseEntity.ok(dentistaService.updateStatusCita(request));
    }

    @GetMapping("/getCitasMes")
    public ResponseEntity<CitasPorMesDTO> getCitasMes(){
        return ResponseEntity.ok(dentistaService.getCitas());
    }

    @PostMapping("/getCitasDay")
    public ResponseEntity<List<CitasPorDiaDetalleDTO>> getDetailsCitas(@RequestBody DiaRequest diaRequest){
        return ResponseEntity.ok(dentistaService.getDetailsCita(diaRequest));
    }

    @GetMapping("getPacientes")
    public ResponseEntity<List<PacientesDTO>> getPacientes(){
        return ResponseEntity.ok(dentistaService.getPacientesAll());
    }

    @PostMapping("getPacientes")
    public ResponseEntity<PacientesDTO> getPaciente(@RequestBody IdRequest idRequest){
        return ResponseEntity.ok(dentistaService.getPacienteById(idRequest));
    }
}
