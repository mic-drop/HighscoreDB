package org.micdrop.highscore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game extends AbstractModel{

    @Column(name = "game_name", unique = true, nullable = false, length = 191)
    private String gameName;

    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Score> scores = new ArrayList<>();

    public Game(){};

    public Game(String gameName){
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Game [game_id=" + super.id + ", game_name=" + gameName + "]";
    }
}