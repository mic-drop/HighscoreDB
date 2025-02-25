package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Game;

public class JpaGameDao extends JpaDao<Game>{

    public JpaGameDao() {
        super(Game.class);
    }
}
