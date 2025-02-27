package org.micdrop.highscore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player")
public class Player extends AbstractModel {

    @Column(name = "player_name", nullable = false, unique = true, length = 191)
    private String playerName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Score> scores = new ArrayList<>();

    public Player() {}

    public Player(String playerName) {
        this.playerName = playerName;
    }

    // Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
