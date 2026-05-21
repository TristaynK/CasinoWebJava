package com.casino.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class BonusId implements Serializable {

    @Column(name = "id_joueur")
    private Integer joueurId;

    @Column(name = "date_bonus")
    private LocalDate dateBonus;

    public BonusId(Integer joueurId, LocalDate dateBonus) {
        this.joueurId = joueurId;
        this.dateBonus = dateBonus;
    }

    public BonusId() {}
}