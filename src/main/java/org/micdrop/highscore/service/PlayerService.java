package org.micdrop.highscore.service;

import org.micdrop.highscore.dao.jpa.PlayerDAO;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaTransactionManager;

import javax.persistence.PersistenceException;
import java.util.Optional;

public class PlayerService {

    private PlayerDAO playerDAO;
    private JpaTransactionManager tx;

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void setTx(JpaTransactionManager tx) {
        this.tx = tx;
    }

    public Player getPlayer(Integer id) {
        try {
            tx.beginRead();
            Player player = Optional.ofNullable(playerDAO.findById(id)).orElseThrow(() -> new IllegalArgumentException("player does not exist"));
            return player;
        } finally {
            tx.commit();
        }
    }
}
