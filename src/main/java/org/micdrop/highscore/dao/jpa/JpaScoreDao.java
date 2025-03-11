package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Score;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

public class JpaScoreDao extends JpaDao<Score>{

    public JpaScoreDao() {
        super(Score.class);
    }

    @Override
    public void delete(Integer id) throws PersistenceException {
            Score deleteScore = findById(id);
            deleteScore.getPlayer().getScores().remove(deleteScore);
            em.remove(deleteScore);
    }
}
