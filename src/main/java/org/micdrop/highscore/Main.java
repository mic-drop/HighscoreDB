package org.micdrop.highscore;

import org.micdrop.highscore.dao.jpa.PlayerDAO;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaSessionManager;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.micdrop.highscore.service.PlayerService;
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
        JpaTransactionManager tm = ctx.getBean("transactionManager", JpaTransactionManager.class);
        PlayerService playerService = new PlayerService();
        PlayerDAO playerDAO = new PlayerDAO(sm);
        playerService.setPlayerDAO(playerDAO);
        playerService.setTx(tm);


        try {
            Player player = playerService.getPlayer(1);
            System.out.println(player.getPlayerName());
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            sm.stopSession();
        }
    }

}

