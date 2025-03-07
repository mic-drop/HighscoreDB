package org.micdrop.highscore;

import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();

        JpaScoreDao jpaScoreDao = ctx.getBean("scoreDao", JpaScoreDao.class);
        if (jpaScoreDao.getEm() == null) {
            System.out.println("Merda");
        }

    }

}

