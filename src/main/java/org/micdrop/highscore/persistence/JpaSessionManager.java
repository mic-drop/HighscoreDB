package org.micdrop.highscore.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class JpaSessionManager {

    private EntityManagerFactory emf;
    private EntityManager em;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private void startSession() {

        if (em == null) {
            em = emf.createEntityManager();
        }
    }
    public void stopSession() {

        if (em != null) {
            em.close();
        }

        em = null;
    }
    public EntityManager getCurrentSession() {
        startSession();
        return em;
    }
}
