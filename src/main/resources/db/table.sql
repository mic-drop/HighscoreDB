CREATE TABLE Game
(
    id        INT PRIMARY KEY AUTO_INCREMENT, -- Unique identifier for each game
    game_name VARCHAR(255) NOT NULL           -- Name of the game
);

CREATE TABLE Player
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(255) NOT NULL,
    game_id     INT          NOT NULL,
    FOREIGN KEY (game_id) REFERENCES Game (id),
    CONSTRAINT unique_player_game UNIQUE (player_name, game_id)
);

CREATE TABLE Score
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    player_id INT NOT NULL,
    game_id   INT NOT NULL,
    score     INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (player_id) REFERENCES Player (id),
    FOREIGN KEY (game_id) REFERENCES Game (id),
    CONSTRAINT unique_score UNIQUE (player_id, game_id, score, date)
);
