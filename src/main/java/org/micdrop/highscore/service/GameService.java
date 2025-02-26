package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.persistence.JpaTransactionManager;

import javax.persistence.PersistenceException;
import java.util.Optional;

public class GameService {
    private JpaTransactionManager tx;
    private JpaGameDao jpaGameDao;

    public Game getPlayer(Integer id){
        try {
            tx.beginRead();
            Game game = jpaGameDao.findById(id);
            return Optional.ofNullable(game).orElseThrow(() -> new IllegalArgumentException("game not found"));
        } finally {
            tx.commit();
        }
    }

    public Game getGameByName(String gameName){
        try {
            tx.beginRead();
            Game game = jpaGameDao.findGameByName(gameName);
            return Optional.ofNullable(game).orElseThrow(() -> new IllegalArgumentException("game name not found"));
        } finally {
            tx.commit();
        }
    }

    public void addGame(String gameName){
      try{
          tx.beginWrite();
          Game game = new Game();
          game.setGameName(gameName);
          jpaGameDao.saveOrUpdate(game);
          tx.commit();
      } catch (PersistenceException e) {
          System.out.println(e.getMessage());
          tx.rollback();
      }
    }

    public void removeGame(Integer id){
       try {
           tx.beginWrite();
           jpaGameDao.delete(id);
           tx.commit();
       } catch (PersistenceException e) {
           System.out.println(e.getMessage());
           tx.rollback();
       }
    }

    public JpaTransactionManager getTx() {
        return tx;
    }

    public void setTx(JpaTransactionManager tx) {
        this.tx = tx;
    }

    public JpaGameDao getJpaGameDao() {
        return jpaGameDao;
    }

    public void setJpaGameDao(JpaGameDao jpaGameDao) {
        this.jpaGameDao = jpaGameDao;
    }
}
