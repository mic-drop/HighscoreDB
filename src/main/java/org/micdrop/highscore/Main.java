package org.micdrop.highscore;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-config.xml");

        //Bean has no id so hibernate recognizes it as new object and not detached
        Game eldenRing = context.getBean("elden ring", Game.class);

        try {
            em.getTransaction().begin();
            em.persist(eldenRing);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
