package org.micrdop.highscore.services;

import org.junit.Before;
import org.junit.Test;
import org.micdrop.highscore.dao.jpa.JpaPlayerDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.model.Player;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.micdrop.highscore.service.PlayerService;

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
    public void testAddPlayer(){

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
    public void testRemoveGame(){
        //setup
        int fakeId = 999;

        //exercise
        playerService.remove(fakeId);

        //verify
        verify(jpaPlayerDao, times(1)).delete(fakeId);
    }
}
