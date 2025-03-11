package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreService {

    private JpaScoreDao jpaScoreDao;
    private JpaPlayerDao jpaPlayerDao;
    private JpaGameDao jpaGameDao;


    public Score get(Integer id) {

        Score score = jpaScoreDao.findById(id);

        return Optional.ofNullable(score).orElseThrow(() -> new IllegalArgumentException("score not found"));
    }

    public List<Score> getScoresByPlayer(String username) {

        return Optional.ofNullable(jpaGameDao.findGameByName(username).getScores()).orElseThrow(() -> new IllegalArgumentException("player username not found"));
    }

    @Transactional
    public Integer addScore(int scoreValue, String username, String gameName) {
        Score newScore = new Score(scoreValue);

        Player player = jpaPlayerDao.findByName(username);
        player = player == null ? new Player(username) : player;
        player.getScores().add(newScore);

        Game game = jpaGameDao.findGameByName(gameName);
        game = game == null ? new Game(gameName) : game;
        game.getScores().add(newScore);

        newScore.setPlayer(player);
        newScore.setGame(game);

        return jpaScoreDao.saveOrUpdate(newScore).getId();
    }

    @Transactional
    public void deleteScore(int id) {
        try {
            Score score = jpaScoreDao.findById(id);
            score.getPlayer().getScores().remove(score);
            jpaScoreDao.delete(id);
        } catch (PersistenceException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void setJpaScoreDao(JpaScoreDao jpaScoreDao) {
        this.jpaScoreDao = jpaScoreDao;
    }

    public void setJpaPlayerDao(JpaPlayerDao jpaPlayerDao) {
        this.jpaPlayerDao = jpaPlayerDao;
    }

    public void setJpaGameDao(JpaGameDao jpaGameDao) {
        this.jpaGameDao = jpaGameDao;
    }

}
