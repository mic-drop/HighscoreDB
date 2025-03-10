package org.micrdop.highscore.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScoreJpaDaoIntegrationTest extends JpaIntegrationTestHelper {
    private JpaScoreDao jpaScoreDao;

    @Before
    public void setup() {
        jpaScoreDao = new JpaScoreDao();
        jpaScoreDao.setEm(em);
    }

    @Test
    public void testFindById() {
        int id = 1;

        Score testScore = jpaScoreDao.findById(id);

        Assert.assertNotNull(testScore);
        Assert.assertEquals(90000, testScore.getScore().intValue());
        Assert.assertEquals(1, testScore.getPlayer().getId().intValue());
        Assert.assertEquals("Mic", testScore.getPlayer().getPlayerName());
    }

    @Test
    public void testFindAll() {

        List<Score> testList = jpaScoreDao.findAll();

        Assert.assertFalse(testList.isEmpty());
        Assert.assertEquals(2, testList.size());
        Assert.assertEquals(90000, testList.get(0).getScore().intValue());
        Assert.assertEquals(1, testList.get(0).getId().intValue());
        Assert.assertEquals("Mic", testList.get(0).getPlayer().getPlayerName());
    }

    @Test
    public void testSaveUpdate() {
        //setup
        JpaGameDao jpaGameDao = new JpaGameDao();
        JpaPlayerDao jpaPlayerDao = new JpaPlayerDao();
        jpaGameDao.setEm(em);
        jpaPlayerDao.setEm(em);

        Score score = new Score();
        int fakeId = 1;
        Player player = jpaPlayerDao.findById(fakeId);
        Game game = jpaGameDao.findById(fakeId);

        score.setScore(777);
        score.setGame(game);
        score.setPlayer(player);
        score.setDate(LocalDateTime.now());

        //exercise
        em.getTransaction().begin();
        int id = jpaScoreDao.saveOrUpdate(score).getId();
        em.getTransaction().commit();

        //verify
        Assert.assertNotNull(jpaScoreDao.findById(id));
        Assert.assertEquals("id game should be the same", fakeId, score.getGame().getId().intValue());
        Assert.assertEquals("player id should be the same", fakeId, score.getPlayer().getId().intValue());
    }

    @Test
    public void testDelete(){
        //setup
        //check test-data seeds
        int deleteId = 2;

        //exercise
        em.getTransaction().begin();
        jpaScoreDao.delete(deleteId);
        em.getTransaction().commit();


        //verify
        Assert.assertNull(em.find(Score.class, deleteId));
        Assert.assertNotNull(em.find(Score.class, 1));
        Assert.assertEquals(jpaScoreDao.findAll().size(), 1);

        Player player = em.find(Player.class, 1);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getPlayerName(), "Mic");
    }

}
