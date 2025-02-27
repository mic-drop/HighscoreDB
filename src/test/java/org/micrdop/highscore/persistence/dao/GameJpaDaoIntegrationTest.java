package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.model.Game;

public class GameJpaDaoIntegrationTest extends JpaIntegrationTestHelper {

    private JpaGameDao jpaGameDao;

    @Before
    public void setup() {

        jpaGameDao = new JpaGameDao();
        jpaGameDao.setSm(sm);
    }

    @Test
    public void findById(){

        int id = 1;

        Game testGame = jpaGameDao.findById(id);

        Assert.assertNotNull(testGame);
        Assert.assertEquals("Snake", testGame.getGameName());
        Assert.assertEquals(1, testGame.getId().intValue());

    }

}
