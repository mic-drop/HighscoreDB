package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaTransactionManager;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class PlayerService {

    private JpaPlayerDao jpaPlayerDao;
    private JpaTransactionManager tx;

    public void setJpaPlayerDao(JpaPlayerDao jpaPlayerDao) {
        this.jpaPlayerDao = jpaPlayerDao;
    }

    public void setTx(JpaTransactionManager tx) {
        this.tx = tx;
    }

    public Integer addPlayer(String name) {

        Integer id = null;
        Player player = new Player();
        player.setPlayerName(name);

        try {
            tx.beginWrite();
            id = jpaPlayerDao.saveOrUpdate(player).getId();
            tx.commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return id;
    }

    public Player getPlayer(Integer id) {
        try {
            tx.beginRead();
            Player player = jpaPlayerDao.findById(id);
            return Optional.ofNullable(player).orElseThrow(() -> new IllegalArgumentException("player not found"));
        } finally {
            tx.commit();
        }
    }

    public Player getPlayerByName(String name) {
        try {
            tx.beginRead();
            Player player = jpaPlayerDao.findByName(name);
            return Optional.ofNullable(player).orElseThrow(() -> new IllegalArgumentException("player name not found"));
        } finally {
            tx.commit();
        }
    }

    public boolean remove(Integer id) {
        try {
            tx.beginWrite();
            jpaPlayerDao.delete(id);
            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            tx.rollback();
            return false;
        }
    }
}
