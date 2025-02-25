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

        PlayerService playerService = ctx.getBean("playerService", PlayerService.class);


        try {
            Player player = playerService.getPlayer(1);
            System.out.println(player.getPlayerName());
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

}

