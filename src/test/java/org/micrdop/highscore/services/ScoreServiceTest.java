package org.micrdop.highscore.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.dao.jpa.JpaScoreDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.service.ScoreService;

import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
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
        //setup
        int fakeId = 999;
        Player player = mock(Player.class);
        Game game = mock(Game.class);
        List<Score> playerScores = new ArrayList<>();
        Score score = new Score(player, game, 999);

        score.setId(fakeId);
        playerScores.add(score);
        player.setScores(playerScores);

        when(jpaScoreDao.findById(fakeId)).thenReturn(score);
        when(player.getScores()).thenReturn(playerScores);

        //exercise
        scoreService.deleteScore(fakeId);

        //verify
        verify(jpaScoreDao, times(1)).delete(fakeId);
        verify(player, times(1)).getScores();
        assertFalse("score should be removed from player's score", player.getScores().contains(score));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveScoreNotFound(){
        //setup
        int fakeId = 999;

        //exercise
        scoreService.deleteScore(fakeId);

        //verify
        verify(jpaScoreDao, never()).delete(fakeId);
        verify(any(Score.class), never()).getPlayer();
    }

}



