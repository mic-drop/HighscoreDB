package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.model.Game;

import java.util.List;

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

    @Test
    public void findByName(){

        String testName = "Snake";

        Game testGame = jpaGameDao.findGameByName(testName);

        Assert.assertNotNull(testGame);
        Assert.assertEquals(testName, testGame.getGameName());
        Assert.assertEquals(1, testGame.getId().intValue());

    }

    @Test
    public void findAll(){
        List<Game> testList;

        testList = jpaGameDao.findAll();

        Assert.assertFalse(testList.isEmpty());
        Assert.assertEquals(1, testList.get(0).getId().intValue());
        Assert.assertEquals("Snake", testList.get(0).getGameName());
        Assert.assertEquals(2, testList.get(1).getId().intValue());
        Assert.assertEquals("Super Mario Sunshine", testList.get(1).getGameName());
    }

    @Test
    public void saveOrUpdate(){
        Game testGame = new Game();
        testGame.setGameName("Elden Wing");

        tx.beginWrite();
        testGame = jpaGameDao.saveOrUpdate(testGame);
        tx.commit();

        Assert.assertNotNull(testGame.getId());
        Assert.assertEquals(3, testGame.getId().intValue());
        Assert.assertEquals("Elden Wing", testGame.getGameName());


    }
}
