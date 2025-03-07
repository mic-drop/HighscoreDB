package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreJpaDaoIntegrationTest extends JpaIntegrationTestHelper{
    private JpaScoreDao jpaScoreDao;

    @Before
    public void setup(){
        jpaScoreDao = new JpaScoreDao();
        jpaScoreDao.setEm(em);
    }

    @Test
    public void testFindById(){
        int id = 1;

        Score testScore = jpaScoreDao.findById(id);

        Assert.assertNotNull(testScore);
        Assert.assertEquals(90000, testScore.getScore().intValue());
        Assert.assertEquals(1, testScore.getPlayer().getId().intValue());
        Assert.assertEquals("Mic", testScore.getPlayer().getPlayerName());
    }

    @Test
    public void testFindAll(){

        List<Score> testList = jpaScoreDao.findAll();

        Assert.assertFalse(testList.isEmpty());
        Assert.assertEquals(1, testList.size());
        Assert.assertEquals(90000, testList.get(0).getScore().intValue());
        Assert.assertEquals(1, testList.get(0).getId().intValue());
        Assert.assertEquals("Mic", testList.get(0).getPlayer().getPlayerName());
    }


}
