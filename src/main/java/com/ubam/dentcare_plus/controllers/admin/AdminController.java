package com.ubam.dentcare_plus.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.dto.admin.DentistaRegistration;
import com.ubam.dentcare_plus.dto.common.MessageResponse;
import com.ubam.dentcare_plus.services.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add-dentista")
    public ResponseEntity<MessageResponse> addDentista(@RequestBody DentistaRegistration request){
        return ResponseEntity.ok(adminService.registerDentista(request));
    }
}
