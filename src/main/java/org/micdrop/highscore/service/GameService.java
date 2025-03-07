package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.Optional;

public class GameService {
    private JpaGameDao jpaGameDao;

    public Game getGame(Integer id) {
        Game game = jpaGameDao.findById(id);
        return Optional.ofNullable(game).orElseThrow(() -> new IllegalArgumentException("game not found"));
    }

    public Game getGameByName(String gameName) {
        Game game = jpaGameDao.findGameByName(gameName);
        return Optional.ofNullable(game).orElseThrow(() -> new IllegalArgumentException("game name not found"));
    }

    @Transactional
    public Integer addGame(String gameName) {

        Integer id = null;

        Game game = new Game();
        game.setGameName(gameName);
        id = jpaGameDao.saveOrUpdate(game).getId();

        return id;
    }

    @Transactional
    public void removeGame(Integer id) {
        jpaGameDao.delete(id);
    }

    public JpaGameDao getJpaGameDao() {
        return jpaGameDao;
    }

    public void setJpaGameDao(JpaGameDao jpaGameDao) {
        this.jpaGameDao = jpaGameDao;
    }
}
