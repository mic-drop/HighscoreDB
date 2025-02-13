package org.micdrop.highscore;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        Score score = new Score();
        score.setGame(em.find(Game.class, 1));
        score.setPlayer(em.find(Player.class, 1));
        score.setScore(800);
        score.setDate(LocalDateTime.now());

        try {
            em.getTransaction().begin();
            em.persist(score);
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
