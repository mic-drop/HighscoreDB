package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.persistence.JpaTransactionManager;

import javax.persistence.PersistenceException;

public class ScoreService {

    private JpaScoreDao jpaScoreDao;
    private JpaPlayerDao jpaPlayerDao;
    private JpaGameDao jpaGameDao;

    private JpaTransactionManager tx;

    public void addScore(int scoreValue, String username, String gameName) {


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
        try {
            tx.beginWrite();
            jpaScoreDao.saveOrUpdate(newScore);
            tx.commit();
        } catch (PersistenceException exception) {
            System.out.println(exception.getMessage());
            tx.rollback();
        }

    }


    public void setJpaScoreDao(JpaScoreDao jpaScoreDao) {
        this.jpaScoreDao = jpaScoreDao;
    }

    public void setTx(JpaTransactionManager tx) {
        this.tx = tx;
    }
}
