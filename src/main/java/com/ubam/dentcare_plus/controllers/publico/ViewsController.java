package com.ubam.dentcare_plus.controllers.publico;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {
    
    @GetMapping("/")
    public String index(){
        return "public/index";
    }
    @GetMapping("/nosotros")
    public String nosotros(){
        return "public/nosotros";
    }
    @GetMapping("/servicios")
    public String servicios(){
        return "public/servicios";
    }
    @GetMapping("/login")
    public String login(){
        return "public/login";
    }
    @GetMapping("/registro")
    public String registro(){
        return "public/registro";
    }
}
