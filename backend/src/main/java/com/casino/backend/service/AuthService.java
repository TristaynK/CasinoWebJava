package com.casino.backend.service;

import com.casino.backend.model.Joueur;
import com.casino.backend.repository.JoueurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JoueurRepository joueurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(String pseudo, String email, String motDePasse) {
        if (joueurRepository.existsByEmail(email)) {
            throw new RuntimeException("Email déjà utilisé");
        }
        if (joueurRepository.existsByPseudo(pseudo)) {
            throw new RuntimeException("Pseudo déjà utilisé");
        }

        Joueur joueur = new Joueur();
        joueur.setPseudo(pseudo);
        joueur.setEmail(email);
        joueur.setMotDePasse(passwordEncoder.encode(motDePasse));

        joueur = joueurRepository.save(joueur);

        return jwtService.generateToken(joueur.getId());

    }

    public String login(String email, String motDePasse) {
        Joueur joueur = joueurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(motDePasse, joueur.getMotDePasse())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        joueur.setDerniereConnexion(LocalDateTime.now());
        joueurRepository.save(joueur);

        return jwtService.generateToken(joueur.getId());
    }

    public Joueur me(Integer id) {
        return joueurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Joueur introuvable"));
    }
}