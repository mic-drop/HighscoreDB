package org.micdrop.highscore.model;

import javax.persistence.*;

@Entity
@Table(name = "Player")
public class Player extends AbstractModel{


    @Column(name = "player_name", nullable = false, length = 255)  // NOT NULL constraint
    private String playerName;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_player_game"))
    private Game game;  // Relationship with Game entity

    // Constructors
    public Player() {}

    public Player(String playerName, Game game) {
        this.playerName = playerName;
        this.game = game;
    }

    // Getters and Setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // toString method
    @Override
    public String toString() {
        return "Player [playerId=" + super.id + ", playerName=" + playerName + ", game=" + (game != null ? game.getId() : "null") + "]";
    }
}
