package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Score;

public class JpaScoreDao extends JpaDao<Score>{

    public JpaScoreDao() {
        super(Score.class);
    }
}
