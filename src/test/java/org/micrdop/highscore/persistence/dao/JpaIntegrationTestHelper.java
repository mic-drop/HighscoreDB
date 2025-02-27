package org.micrdop.highscore.persistence.dao;

import org.junit.After;
import org.junit.Before;
import org.micdrop.highscore.persistence.JpaSessionManager;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.springframework.context.support.GenericXmlApplicationContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

public class JpaIntegrationTestHelper {

    protected GenericXmlApplicationContext ctx;
    protected EntityManagerFactory emf;
    protected JpaSessionManager sm;
    protected JpaTransactionManager tx;

    @Before
    public void init() {

        ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");

        ctx.load("/spring/spring-config.xml");
        ctx.refresh();


        emf = ctx.getBean(EntityManagerFactory.class);

        sm = new JpaSessionManager();
        tx = new JpaTransactionManager();

        sm.setEmf(emf);
        tx.setSm(sm);

        tx.beginRead();
    }


    @After
    public void tearDown() {

        try {
            tx.commit();
        } catch (PersistenceException e) {
            tx.rollback();
        }

        if (emf != null) {
            emf.close();
        }
        ctx.destroy();
    }

}
