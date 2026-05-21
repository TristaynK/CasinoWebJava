package com.casino.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.casino.backend.repository.BonusRepository;
import com.casino.backend.repository.JoueurRepository;

import java.time.LocalDate;
import java.math.BigDecimal;

import com.casino.backend.model.Bonus;
import com.casino.backend.model.Joueur;

@Service
@RequiredArgsConstructor
public class BonusService {

    private final BonusRepository bonusRepository;
    private final JoueurRepository joueurRepository;


    public void claimBonus(Integer joueurId) {

        Joueur joueur = joueurRepository.findById(joueurId)
            .orElseThrow(() -> new RuntimeException("Joueur introuvable"));


        if (bonusRepository.existsByIdJoueurIdAndIdDateBonus(joueurId, LocalDate.now())) {
            throw new RuntimeException("Bonus déjà réclamé pour aujourd'hui");
        }
        bonusRepository.save(new Bonus(joueurId, LocalDate.now()));
        
        joueur.setArgent(joueur.getArgent().add(BigDecimal.valueOf(50)));
        joueurRepository.save(joueur);      
    }




}