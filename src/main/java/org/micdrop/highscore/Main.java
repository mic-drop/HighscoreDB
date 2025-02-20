package org.micdrop.highscore;

import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.PersistenceException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();

        JpaTransactionManager tx = ctx.getBean("transactionManager", JpaTransactionManager.class);
        Game eldenRing = ctx.getBean("eldenRing", Game.class);
        System.out.println(eldenRing.getGameName());


    }

}

