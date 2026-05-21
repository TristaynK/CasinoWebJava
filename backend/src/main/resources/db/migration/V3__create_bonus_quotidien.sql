CREATE TABLE IF NOT EXISTS bonus_quotidien (
    id_joueur INT NOT NULL REFERENCES joueurs(id),
    date_bonus DATE NOT NULL,
    UNIQUE(id_joueur, date_bonus)
);