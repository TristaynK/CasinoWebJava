package com.casino.backend.repository;

import com.casino.backend.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Integer> {
    Optional<Joueur> findByEmail(String email);
    Optional<Joueur> findByPseudo(String pseudo);
    Optional<Joueur> findById(Integer id);
    boolean existsByEmail(String email);
    boolean existsByPseudo(String pseudo);
    boolean existsById(Integer id);
}