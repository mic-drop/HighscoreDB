package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaSessionManager;

import java.util.List;

public class PlayerDAO extends JpaDao<Player> {

    public PlayerDAO(JpaSessionManager sm) {
        super(Player.class, sm);
    }

}
