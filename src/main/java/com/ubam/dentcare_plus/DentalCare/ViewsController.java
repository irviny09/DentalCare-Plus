package com.ubam.dentcare_plus.DentalCare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
