package org.micrdop.highscore.persistence.dao;

import org.junit.After;
import org.junit.Before;
import org.micdrop.highscore.persistence.JpaSessionManager;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

public class JpaIntegrationTestHelper {

    protected GenericXmlApplicationContext ctx;
    protected EntityManagerFactory emf;
    protected EntityManager em;

    @Before
    public void init() {

        ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();


        emf = ctx.getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();

    }


    @After
    public void tearDown() {

        if(em != null) {
            em.clear();
            em.close();
        }

        if (emf != null) {
            emf.close();
        }
        ctx.destroy();
    }

}
