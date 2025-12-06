package com.beauty.pro.ia.agendamento.controllers;

import com.beauty.pro.ia.agendamento.dto.AuthResponseDTO;
import com.beauty.pro.ia.agendamento.dto.LoginDTO;
import com.beauty.pro.ia.agendamento.entities.User;
import com.beauty.pro.ia.agendamento.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody User user) {
        String token = service.register(user);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        String token = service.login(dto);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
