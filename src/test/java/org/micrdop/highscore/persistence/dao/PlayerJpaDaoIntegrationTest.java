package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PlayerJpaDaoIntegrationTest extends JpaIntegrationTestHelper {

    private JpaPlayerDao jpaPlayerDao;

    @Before
    public void setup() {
        jpaPlayerDao = new JpaPlayerDao();
        jpaPlayerDao.setEm(em);
    }

    @Test
    public void findById() {

        int id = 1;

        Player testPlayer = jpaPlayerDao.findById(id);

        Assert.assertNotNull(testPlayer);
        Assert.assertEquals("Mic", testPlayer.getPlayerName());
        Assert.assertEquals(1, testPlayer.getId().intValue());

    }

    @Test
    public void findByName() {

        String testName = "Mic";

        Player testPlayer = jpaPlayerDao.findByName(testName);

        Assert.assertNotNull(testPlayer);
        Assert.assertEquals(testName, testPlayer.getPlayerName());
        Assert.assertEquals(1, testPlayer.getId().intValue());

    }

    @Test
    public void findAll() {

        List<Player> testList;
        //Change this value according to resources/db/test-data.sql
        int playerSeeds = 3;

        testList = jpaPlayerDao.findAll();

        Assert.assertFalse(testList.isEmpty());
        Assert.assertEquals(playerSeeds, testList.size());
        Assert.assertEquals(1, testList.get(0).getId().intValue());
        Assert.assertEquals("Mic", testList.get(0).getPlayerName());
        Assert.assertEquals(2, testList.get(1).getId().intValue());
        Assert.assertEquals("Drop", testList.get(1).getPlayerName());
    }

    @Test
    public void saveOrUpdate() {
        Player testPlayer = new Player();
        testPlayer.setPlayerName("New player");

        em.getTransaction().begin();
        testPlayer = jpaPlayerDao.saveOrUpdate(testPlayer);
        em.getTransaction().commit();


        Assert.assertNotNull(testPlayer.getId());
        Assert.assertEquals(4, testPlayer.getId().intValue());
        Assert.assertEquals("New player", testPlayer.getPlayerName());
    }

    @Test
    public void deletePlayerNoScore() {
        int id = 3;

        em.getTransaction().begin();
        jpaPlayerDao.delete(id);
        em.getTransaction().commit();

        Assert.assertNull("player should be null", em.find(Player.class, id));
    }

    /*Verifies orphan removal*/
    @Test
    public void deletePlayerWithScore() {
        //setup
        int id = 1;

        //exercise
        em.getTransaction().begin();
        jpaPlayerDao.delete(id);
        em.getTransaction().commit();

        //verify
        JpaScoreDao jpaScoreDao;
        jpaScoreDao = new JpaScoreDao();
        jpaScoreDao.setEm(em);

        Assert.assertNull("player should be null", em.find(Player.class, id));
        Assert.assertEquals(0, jpaScoreDao.findAll().size());
    }

}
