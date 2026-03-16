package com.ubam.dentcare_plus.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubam.dentcare_plus.dto.auth.TokenDTO;
import com.ubam.dentcare_plus.dto.auth.LoginDTO;
import com.ubam.dentcare_plus.dto.auth.RegistroDTO;
import com.ubam.dentcare_plus.services.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegistroDTO request){
        return ResponseEntity.ok(authService.register(request));
    }
}
