package org.micdrop.highscore.model;

import javax.persistence.*;

@Entity
@Table(name = "Game")
public class Game extends AbstractModel{


    private String game_name;

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }


    @Override
    public String toString() {
        return "Game [game_id=" + super.id + ", game_name=" + game_name + "]";
    }
}