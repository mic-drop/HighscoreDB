package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Player;

import java.util.List;

public class PlayerDAO extends JpaDao<Player> {

    public PlayerDAO() {
        super(Player.class);
    }
}
