package org.micdrop.highscore;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.persistence.JpaSessionManager;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();

        JpaSessionManager sm = ctx.getBean("sessionManager", JpaSessionManager.class);
        Game eldenRing = ctx.getBean("eldenRing", Game.class);
        System.out.println(eldenRing.getGameName());
        try {
            EntityManager em = sm.getCurrentSession();
            em.getTransaction().begin();
            em.merge(eldenRing);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            sm.stopSession();
        }
    }

}

