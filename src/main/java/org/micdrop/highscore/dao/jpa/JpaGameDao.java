package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaGameDao extends JpaDao<Game>{

    public JpaGameDao() {
        super(Game.class);
    }

    public Game findGameByName(String name){

        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);

        query.select(root)
                .where(builder.equal(root.get("gameName"), name));

        return sm.getCurrentSession().createQuery(query).getSingleResult();

    }
}
