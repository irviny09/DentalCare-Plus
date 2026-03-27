package com.ubam.dentcare_plus.controllers.dentista;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubam.dentcare_plus.services.DentistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dentista")
public class DentistaViewController {

    private final DentistaService dentistaService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        var citasMes = dentistaService.getCitas();
        var citasDay = dentistaService.getCitasDay();
        var canceladas = dentistaService.getCitasCanceladas();
        var pendientesManana = dentistaService.getCitasPendientes();
        var nextPaciente = dentistaService.gPaciente();

        model.addAttribute("doctor" , dentistaService.gDentista().getUser().getName());

        model.addAttribute("total", citasMes != null ? citasMes.getTotal() : 0);
        model.addAttribute("diferencia", citasMes != null ? citasMes.getDiferencia() : 0);

        model.addAttribute("totalDay", citasDay != null ? citasDay.getTotal() : 0);
        model.addAttribute("pendientes", citasDay != null ? citasDay.getPendientes() : 0);

        model.addAttribute("canceladas", canceladas != null ? canceladas.getCanceladas() : 0);

        model.addAttribute("pendientesManana", pendientesManana != null ? pendientesManana.getPendientes() : 0);

        model.addAttribute("namePaciente" , nextPaciente != null ? nextPaciente.getPaciente() : "");
        model.addAttribute("hourPaciente", nextPaciente != null ? nextPaciente.getHora() : "");
        model.addAttribute("tratamientoPaciente", nextPaciente != null ? nextPaciente.getTratamiento() : "");

        return "dentistas/dashboard";
    }

    @GetMapping("/citas")
    public String citas(){
        return "dentistas/citas";
    }

    @GetMapping("/pacientes")
    public String pacientes(){
        return "dentistas/pacientes";
    }

}
