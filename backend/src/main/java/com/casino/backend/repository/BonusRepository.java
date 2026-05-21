package com.casino.backend.repository;

import com.casino.backend.model.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Integer> {
    boolean existsByIdJoueurIdAndIdDateBonus(Integer joueurId, LocalDate dateBonus);
}
