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
    public String dashboard(Model model){
        model.addAttribute("total", dentistaService.getCitas().getTotal());
        model.addAttribute("diferencia", dentistaService.getCitas().getDiferencia());
        model.addAttribute("totalDay" , dentistaService.getCitasDay().getTotal());
        model.addAttribute("pendientes", dentistaService.getCitasDay().getPendientes());
        model.addAttribute("canceladas", dentistaService.getCitasCanceladas().getCanceladas());
        model.addAttribute("pendientesManana" , dentistaService.getCitasPendientes().getPendientes());
        return "dentistas/dashboard";
    }
    
}
