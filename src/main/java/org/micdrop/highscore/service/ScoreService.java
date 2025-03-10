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
import java.util.Optional;

public class ScoreService {

    private JpaScoreDao jpaScoreDao;
    private JpaPlayerDao jpaPlayerDao;
    private JpaGameDao jpaGameDao;


    public Score get(Integer id) {

        Score score = jpaScoreDao.findById(id);

        return Optional.ofNullable(score).orElseThrow(() -> new IllegalArgumentException("score not found"));
    }

    @Transactional
    public Integer addScore(int scoreValue, String username, String gameName) {
        Player player = jpaPlayerDao.findByName(username);
        if (player == null) {
            player = new Player(username);
        }

        Game game = jpaGameDao.findGameByName(gameName);
        if (game == null) {
            game = new Game();
            game.setGameName(gameName);
        }

        Score newScore = new Score(player, game, scoreValue);
        return jpaScoreDao.saveOrUpdate(newScore).getId();
    }

    @Transactional
    public void deleteScore(int id){
        jpaScoreDao.delete(id);
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
