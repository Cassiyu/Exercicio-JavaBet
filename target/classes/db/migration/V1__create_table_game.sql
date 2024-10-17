CREATE TABLE games (
       id SERIAL PRIMARY KEY,
       home_team VARCHAR(255) NOT NULL,
       away_team VARCHAR(255) NOT NULL,
       home_team_score INT DEFAULT 0,
       away_team_score INT DEFAULT 0
);