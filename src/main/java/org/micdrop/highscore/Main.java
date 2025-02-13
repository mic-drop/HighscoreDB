package org.micdrop.highscore;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
      Player player = new Player();
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
      EntityManager em = emf.createEntityManager();


      player.setPlayerName("Mic Drop");

      player.setGame(em.find(Game.class, 1));

      em.getTransaction().begin();
      em.persist(player);
      em.getTransaction().commit();
      em.close();
      emf.close();
    }
}
