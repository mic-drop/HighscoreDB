package org.micdrop.highscore.model;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player extends AbstractModel {

    @Column(name = "player_name", nullable = false, unique = true, length = 191)
    private String playerName;

    // Constructors
    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    // Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
