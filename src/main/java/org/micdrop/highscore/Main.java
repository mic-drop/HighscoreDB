package org.micdrop.highscore;

import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.service.PlayerService;
import org.micdrop.highscore.service.ScoreService;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.PersistenceException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();

        ScoreService scoreService = ctx.getBean("scoreService", ScoreService.class);


        try {
            Score score = scoreService.get(1);
            System.out.println(score.getDate());
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
    }

}

