CREATE TYPE nom_jeu AS ENUM ('blackjack', 'slots', 'roulette');

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    id_joueur INT NOT NULL REFERENCES joueurs(id),
    jeu nom_jeu NOT NULL,
    mise DECIMAL(10,2) NOT NULL,
    resultat DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    date_transaction TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);