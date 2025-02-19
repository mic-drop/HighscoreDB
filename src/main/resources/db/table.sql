CREATE TABLE game
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    game_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE player
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(255) UNIQUE NOT NULL,
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
