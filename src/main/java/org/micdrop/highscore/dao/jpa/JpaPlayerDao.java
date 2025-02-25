package org.micdrop.highscore.dao.jpa;

import org.micdrop.highscore.model.Player;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaPlayerDao extends JpaDao<Player> {

    public JpaPlayerDao() {
        super(Player.class);
    }

    public Player findByName(String name) throws PersistenceException {
        CriteriaBuilder builder = sm.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);

        query.select(root)
                .where(builder.equal(root.get("player_name"), name));

        return sm.getCurrentSession().createQuery(query).getSingleResult();
    }
}
