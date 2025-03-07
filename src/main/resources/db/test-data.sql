--#2 Games
INSERT INTO game (id, game_name) VALUES (1, 'Snake');
INSERT INTO game (id, game_name) VALUES (2, 'Super Mario Sunshine');
--#3 Players
INSERT INTO player (id, player_name) VALUES (1, 'Mic');
INSERT INTO player (id, player_name) VALUES (2, 'Drop');
INSERT INTO player (id, player_name) VALUES (3, 'player to delete');
--#2 Score
INSERT INTO score (id, date, score, game_id, player_id) VALUES (1, TIMESTAMP '2025-02-02 08:45:56.468', 90000, 2, 1);
--score to delete
INSERT INTO score (id, date, score, game_id, player_id) VALUES (2, TIMESTAMP '2025-02-02 08:45:56.468', 0, 1, 1);
