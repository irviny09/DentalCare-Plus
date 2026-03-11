package com.ubam.dentcare_plus.DentalCare;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @PostMapping(value = "test")
    public String algo(){
        return "test private";
    }
}
