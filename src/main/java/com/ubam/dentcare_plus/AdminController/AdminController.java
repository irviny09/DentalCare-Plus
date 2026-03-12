package com.ubam.dentcare_plus.AdminController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add-dentista")
    public ResponseEntity<AdminResponse> addDentista(@RequestBody AdminRequest request){
        return ResponseEntity.ok(adminService.registerDentista(request));
    }
}
