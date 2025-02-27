package org.micdrop.highscore;

import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.service.PlayerService;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.PersistenceException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();

        PlayerService playerService = ctx.getBean("playerService", PlayerService.class);


        try {
            Player player = playerService.getPlayer(1);
            List<Score> scores = player.getScores();
            for(Score score : scores)
            {
                System.out.println(score.getDate());
            }
            playerService.remove(1);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

}

