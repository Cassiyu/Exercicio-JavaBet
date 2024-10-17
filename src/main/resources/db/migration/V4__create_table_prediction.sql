CREATE TABLE predictions (
    id SERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    predicted_home_team_score INT NOT NULL,
    predicted_away_team_score INT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);