package org.micrdop.highscore.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.service.ScoreService;

import javax.persistence.PersistenceException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ScoreServiceTest {

    private ScoreService scoreService;
    private JpaScoreDao jpaScoreDao;
    private JpaGameDao jpaGameDao;
    private JpaPlayerDao jpaPlayerDao;

    @Before
    public void setup() {
        jpaScoreDao = mock(JpaScoreDao.class);
        jpaGameDao = mock(JpaGameDao.class);
        jpaPlayerDao = mock(JpaPlayerDao.class);

        scoreService = new ScoreService();
        scoreService.setJpaScoreDao(jpaScoreDao);
        scoreService.setJpaGameDao(jpaGameDao);
        scoreService.setJpaPlayerDao(jpaPlayerDao);
    }

    @Test
    public void testFindById() {
        int fakeId = 999;

        Score fakeScore = new Score();
        fakeScore.setId(fakeId);
        fakeScore.setScore(999);
        when(jpaScoreDao.findById(fakeId)).thenReturn(fakeScore);

        Score score = scoreService.get(fakeId);

        assertNotNull(score);
        assertEquals(fakeId, score.getId().intValue());
        verify(jpaScoreDao, times(1)).findById(fakeId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetScoreNotFound() {
        when(jpaScoreDao.findById(1)).thenReturn(null);

        scoreService.get(1);
    }

    @Test
    public void testAddScore() {
        int fakeId = 999;
        String game = "Snake";
        String player = "Mic";
        Score score = new Score();
        score.setId(fakeId);

        when(jpaScoreDao.saveOrUpdate(any(Score.class))).thenReturn(score);

        int id = scoreService.addScore(999, player, game);

        assertEquals(fakeId, id);
        verify(jpaPlayerDao, times(1)).findByName(player);
        verify(jpaGameDao, times(1)).findGameByName(game);
        verify(jpaScoreDao, times(1)).saveOrUpdate(any(Score.class));
    }

    @Test
    public void testRemoveScore() {
        int fakeId = 999;

        scoreService.deleteScore(fakeId);

        verify(jpaScoreDao, times(1)).delete(fakeId);
    }

    @Test
    public void testRemoveScoreNotFound(){
        int fakeId = 999;
        doThrow(new PersistenceException("score not found")).when(jpaScoreDao).delete(fakeId);

        scoreService.deleteScore(fakeId);

        verify(jpaScoreDao, times(1)).delete(fakeId);
    }
}



