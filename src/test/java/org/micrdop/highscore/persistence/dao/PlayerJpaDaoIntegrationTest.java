package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;

import java.util.List;

public class PlayerJpaDaoIntegrationTest extends JpaIntegrationTestHelper {

    private JpaPlayerDao jpaPlayerDao;

    @Before
    public void setup(){
        jpaPlayerDao = new JpaPlayerDao();
        jpaPlayerDao.setSm(sm);
    }

    @Test
    public void findById(){

        int id = 1;

        Player testPlayer = jpaPlayerDao.findById(id);

        Assert.assertNotNull(testPlayer);
        Assert.assertEquals("Mic", testPlayer.getPlayerName());
        Assert.assertEquals(1, testPlayer.getId().intValue());

    }

    @Test
    public void findByName(){

        String testName = "Mic";

        Player testPlayer = jpaPlayerDao.findByName(testName);

        Assert.assertNotNull(testPlayer);
        Assert.assertEquals(testName, testPlayer.getPlayerName());
        Assert.assertEquals(1, testPlayer.getId().intValue());

    }

    @Test
    public void findAll(){
        List<Player> testList;

        testList = jpaPlayerDao.findAll();

        Assert.assertFalse(testList.isEmpty());
        Assert.assertEquals(1, testList.get(0).getId().intValue());
        Assert.assertEquals("Mic", testList.get(0).getPlayerName());
        Assert.assertEquals(2, testList.get(1).getId().intValue());
        Assert.assertEquals("Drop", testList.get(1).getPlayerName());
    }

    @Test
    public void saveOrUpdate(){
        Player testPlayer = new Player();
        testPlayer.setPlayerName("New player");

        tx.beginWrite();
        testPlayer = jpaPlayerDao.saveOrUpdate(testPlayer);
        tx.commit();

        Assert.assertNotNull(testPlayer.getId());
        Assert.assertEquals(4, testPlayer.getId().intValue());
        Assert.assertEquals("New player", testPlayer.getPlayerName());
    }

    @Test
    public void delete(){
        int id = 3;

        tx.beginWrite();
        jpaPlayerDao.delete(id);
        tx.commit();

        Assert.assertNull("game should be null",sm.getCurrentSession().find(Player.class, id));
    }

}
