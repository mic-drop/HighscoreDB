package org.micdrop.highscore.persistence;

import javax.persistence.EntityManager;

public class JpaTransactionManager {

    private JpaSessionManager sm;

    public void setSm(JpaSessionManager sm) {
        this.sm = sm;
    }

    public EntityManager beginRead() {
        return sm.getCurrentSession();
    }

    public void beginWrite() {
        sm.getCurrentSession().getTransaction().begin();
    }

    public void commit() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().commit();
        }

        sm.stopSession();
    }

    public void rollback() {

        if (sm.getCurrentSession().getTransaction().isActive()) {
            sm.getCurrentSession().getTransaction().rollback();
        }

        sm.stopSession();
    }

}
