package org.micdrop.highscore.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Score",
uniqueConstraints = @UniqueConstraint(columnNames = {"player_id", "game_id", "score", "date"}))
public class Score extends AbstractModel{
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_score_player"))
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_score_game"))
    private Game game;

    @Column(nullable = false)
    private Integer score;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    // Constructors
    public Score() {
    }

    public Score(Player player, Game game, Integer score, LocalDateTime date) {
        this.player = player;
        this.game = game;
        this.score = score;
        this.date = date;
    }

    // Getters and Setters

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

    // toString
    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", player=" + player.getPlayerName() +
                ", game=" + game.getGame_name() +
                ", score=" + score +
                ", date=" + date +
                '}';
    }

}
