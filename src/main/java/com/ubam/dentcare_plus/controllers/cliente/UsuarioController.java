package com.ubam.dentcare_plus.controllers.cliente;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.dto.cliente.ActividadRecienteDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaNuevaDTO;
import com.ubam.dentcare_plus.dto.cliente.CitaSiguienteDTO;
import com.ubam.dentcare_plus.dto.cliente.EspecialistasDTO;
import com.ubam.dentcare_plus.dto.cliente.FechaDisponibilidadDTO;
import com.ubam.dentcare_plus.dto.cliente.HistorialMedicoDTO;
import com.ubam.dentcare_plus.dto.cliente.HorasOcupadasDTO;
import com.ubam.dentcare_plus.dto.cliente.ServiciosDTO;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/actividad")
    public ResponseEntity<List<ActividadRecienteDTO>> showActivity(){
        return ResponseEntity.ok(usuarioService.showActivity());     
    }
    @GetMapping("/citaSiguiente")
    public ResponseEntity<List<CitaSiguienteDTO>> showCitaSiguiente(){
        return ResponseEntity.ok(usuarioService.showCitaSiguiente());
    }
    @GetMapping("/saldoPendiente")
    public ResponseEntity<BigDecimal> showSaldo(){
        return ResponseEntity.ok(usuarioService.showSaldo());
    }
    @PostMapping("/horasOcupadas")
    public ResponseEntity<List<HorasOcupadasDTO>> showHorasOcupadas(@RequestBody FechaDisponibilidadDTO fechaDisponibilidadDTO){
        return ResponseEntity.ok(usuarioService.getFechasOcupadas(fechaDisponibilidadDTO));
    }
    @GetMapping("/dentistas")
    public ResponseEntity<List<EspecialistasDTO>> getEspecialistas(){
        return ResponseEntity.ok(usuarioService.getEspecialistas());
    }
    @GetMapping("/servicios")
    public ResponseEntity<List<ServiciosDTO>> getServicios(){
        return ResponseEntity.ok(usuarioService.getServicios());
    }
    @PostMapping("/cita")
    public ResponseEntity<MessageResponse> createNewCita(@RequestBody CitaNuevaDTO citaNuevaDTO){
        return ResponseEntity.ok(usuarioService.createNewCita(citaNuevaDTO));
    }
    @GetMapping("/historial-medico")
    public ResponseEntity<List<HistorialMedicoDTO>> showHistorial(){
        return ResponseEntity.ok(usuarioService.getHistorialMedico());
    }
    
}
