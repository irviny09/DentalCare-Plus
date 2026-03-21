package com.ubam.dentcare_plus.controllers.dentista;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dentista")
public class DentistaViewController {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dentistas/dashboard";
    }
    
}
