package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class PlayerService {

    private JpaPlayerDao jpaPlayerDao;

    public void setJpaPlayerDao(JpaPlayerDao jpaPlayerDao) {
        this.jpaPlayerDao = jpaPlayerDao;
    }

    @Transactional
    public Integer addPlayer(String name) {

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

    public List<Score> getScores(Integer id){
        return getPlayer(id).getScores();
    }
}
