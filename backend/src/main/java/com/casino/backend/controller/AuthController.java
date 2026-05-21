package com.casino.backend.controller;

import com.casino.backend.model.Joueur;
import com.casino.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;
import com.casino.backend.model.Joueur;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    record RegisterRequest(String pseudo, String email, String motDePasse) {}
    record LoginRequest(String email, String motDePasse) {}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            String token = authService.register(
                request.pseudo(),
                request.email(),
                request.motDePasse()
            );
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(
                request.email(),
                request.motDePasse()
            );
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        try {
            Integer id = (Integer) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            Joueur joueur = authService.me(id);
            return ResponseEntity.ok(joueur);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    
}