package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class PlayerService {

    private JpaPlayerDao jpaPlayerDao;

    public void setJpaPlayerDao(JpaPlayerDao jpaPlayerDao) {
        this.jpaPlayerDao = jpaPlayerDao;
    }

    @Transactional
    public Integer addPlayer(String name) {

        Integer id = null;
        Player player = new Player();
        player.setPlayerName(name);

        return jpaPlayerDao.saveOrUpdate(player).getId();
    }

    public Player getPlayer(Integer id) {
        Player player = jpaPlayerDao.findById(id);
        return Optional.ofNullable(player).orElseThrow(() -> new IllegalArgumentException("player not found"));
    }

    public Player getPlayerByName(String name) {
        Player player = jpaPlayerDao.findByName(name);
        return Optional.ofNullable(player).orElseThrow(() -> new IllegalArgumentException("player name not found"));
    }

    @Transactional
    public boolean remove(Integer id) {
        jpaPlayerDao.delete(id);
        return true;
    }
}
