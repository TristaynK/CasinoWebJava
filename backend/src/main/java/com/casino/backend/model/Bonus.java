package com.casino.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bonus_quotidien")
public class Bonus {

    @EmbeddedId
    private BonusId id;

    public Bonus(Integer joueurId, LocalDate dateBonus) {
        this.id = new BonusId(joueurId, dateBonus);
    }

    public Bonus() {}
}