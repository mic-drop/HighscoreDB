CREATE TABLE Game (
    game_id INT PRIMARY KEY,  -- Unique identifier for each game
    game_name VARCHAR(255) NOT NULL  -- Name of the game
);

CREATE TABLE Player (
    player_id INT PRIMARY KEY AUTO_INCREMENT,  -- Unique identifier for each player
    player_name VARCHAR(255) NOT NULL,  -- Name of the player
    game_id INT,  -- Foreign key to the Game table
    FOREIGN KEY (game_id) REFERENCES Game(game_id),
    CONSTRAINT unique_player_game UNIQUE (player_name, game_id)  -- Prevent duplicate player names in the same game
);

CREATE TABLE Score (
    score_id INT PRIMARY KEY AUTO_INCREMENT,  -- Unique identifier for each score entry
    game_id INT,  -- Foreign key to the Game table
    player_name VARCHAR(255),  -- Name of the player (could be a foreign key, but for this example, it's kept as a simple reference)
    score INT,  -- Player's score in the game
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp of when the score was recorded
    FOREIGN KEY (game_id) REFERENCES Game(game_id),
    CONSTRAINT unique_score UNIQUE (player_name, game_id, date)  -- Prevent duplicate scores for the same player in the same game on the same day
);
