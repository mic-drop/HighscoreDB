package org.micdrop.highscore.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game extends AbstractModel{

    @Column(name = "game_name", unique = true, nullable = false, length = 191)
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    @Override
    public String toString() {
        return "Game [game_id=" + super.id + ", game_name=" + gameName + "]";
    }
}