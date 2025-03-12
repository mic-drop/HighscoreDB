package org.micrdop.highscore.services;

import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.model.Score;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.micdrop.highscore.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class PlayerServiceTest {


    private PlayerService playerService;
    private JpaPlayerDao jpaPlayerDao;

    @Before
    public void setup() {
        jpaPlayerDao = mock(JpaPlayerDao.class);

        playerService = new PlayerService();
        playerService.setJpaPlayerDao(jpaPlayerDao);
    }

    @Test
    public void testFindById() {

        //setup
        int fakeId = 888;
        Player fakePlayer = mock(Player.class);
        when(jpaPlayerDao.findById(fakeId)).thenReturn(fakePlayer);

        //exercise
        Player testPlayer = playerService.getPlayer(fakeId);

        //verify
        assertEquals(fakePlayer, testPlayer);

    }

    @Test
    public void testGetByName() {

        //setup
        String playerName = "Solid Snake";
        Player fakePlayer = new Player();
        when(jpaPlayerDao.findByName(playerName)).thenReturn(fakePlayer);

        //exercise
        Player testPlayer = playerService.getPlayerByName(playerName);

        //verify
        assertEquals(fakePlayer, testPlayer);
    }

    @Test
    public void testAddPlayer() {

        //setup
        int fakeId = 999;
        Player newPlayer = new Player();
        newPlayer.setId(fakeId);
        when(jpaPlayerDao.saveOrUpdate(any(Player.class))).thenReturn(newPlayer);

        //exercise
        int id = playerService.addPlayer("Not in Seeds");

        //verify
        assertEquals(fakeId, id);
    }

    @Test
    public void testRemovePlayer() {
        //setup
        int fakeId = 999;

        //exercise
        playerService.remove(fakeId);

        //verify
        verify(jpaPlayerDao, times(1)).delete(fakeId);
    }

    @Test
    public void testGetScores() {
        //setup
        int fakeId = 999;
        Player player = mock(Player.class);
        List<Score> scoreList = mock(ArrayList.class);

        when(jpaPlayerDao.findById(fakeId)).thenReturn(player);
        when(player.getScores()).thenReturn(scoreList);
        when(scoreList.size()).thenReturn(1);

        //exercise
        List<Score> returnList = playerService.getScores(fakeId);

        //verify
        verify(jpaPlayerDao, times(1)).findById(fakeId);
        verify(player, times(1)).getScores();
        assertEquals(1, returnList.size());
    }

    @Test
    public void testGetScoresByGameName(){
        //setup
        int fakeId = 999;
        Player player = mock(Player.class);
        List<Score> scores = new ArrayList<>();

        scores.add(new Score(player, new Game("Snake"), 10));
        scores.add(new Score(player, new Game("Pong"), 10));
        scores.add(new Score(player, new Game("Snake"), 100));

        when(jpaPlayerDao.findById(fakeId)).thenReturn(player);
        when(player.getScores()).thenReturn(scores);

        //exercise
        List<Score> returnList = playerService.getScoresByGameName(fakeId, "Snake");

        //verify
        verify(jpaPlayerDao, times(1)).findById(fakeId);
        verify(player, times(1)).getScores();
        assertEquals(2, returnList.size());
    }
}
