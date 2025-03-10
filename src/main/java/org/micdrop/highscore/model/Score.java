package org.micdrop.highscore.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "score",
        uniqueConstraints = @UniqueConstraint(columnNames = {"player_id", "game_id", "score", "date"}))
public class Score extends AbstractModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_score_player"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_score_game"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    public Score() {}

    public Score(Player player, Game game, Integer score) {
        this.player = player;
        this.game = game;
        this.score = score;
        this.date = LocalDateTime.now();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", player=" + player.getPlayerName() +
                ", game=" + game.getGameName() +
                ", score=" + score +
                ", date=" + date +
                '}';
    }

}
