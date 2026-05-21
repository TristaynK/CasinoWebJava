package com.casino.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.casino.backend.service.BonusService; 

import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/bonus")
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @PostMapping("/claim")
    public ResponseEntity<?> claimBonus() {
        try {
            Integer joueurId = (Integer) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
            bonusService.claimBonus(joueurId);
            return ResponseEntity.ok(Map.of("message", "Bonus réclamé avec succès"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}