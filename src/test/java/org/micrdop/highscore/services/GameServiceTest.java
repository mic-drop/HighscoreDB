package org.micrdop.highscore.services;


import org.micdrop.highscore.dao.jpa.JpaGameDao;
import org.micdrop.highscore.model.Game;
import org.micdrop.highscore.persistence.JpaTransactionManager;
import org.micdrop.highscore.service.GameService;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    private GameService gameService;
    private JpaTransactionManager tx;
    private JpaGameDao jpaGameDao;

    @Before
    public void setup() {
        tx = mock(JpaTransactionManager.class);
        jpaGameDao = mock(JpaGameDao.class);

        gameService = new GameService();
        gameService.setJpaGameDao(jpaGameDao);
        gameService.setTx(tx);
    }

    @Test
    public void testGameFindById() {

        //setup
        int fakeId = 888;
        Game fakeGame = mock(Game.class);
        when(jpaGameDao.findById(fakeId)).thenReturn(fakeGame);

        //exercise
        Game testGame = gameService.getGame(fakeId);

        //verify
        verify(tx, times(1)).beginRead();
        verify(tx, times(1)).commit();
        assertEquals(fakeGame, testGame);

    }

    @Test
    public void testGetByName() {

        //setup
        String gameName = "Snake";
        Game fakeGame = new Game();
        when(jpaGameDao.findGameByName(gameName)).thenReturn(fakeGame);

        //exercise
        Game testGame = gameService.getGameByName(gameName);

        //verify
        verify(tx, times(1)).beginRead();
        verify(tx, times(1)).commit();
        assertEquals(fakeGame, testGame);
    }

    @Test
    public void testAddGame(){

        //setup
        int fakeId = 999;
        Game newGame = new Game();
        newGame.setId(fakeId);
        when(jpaGameDao.saveOrUpdate(any(Game.class))).thenReturn(newGame);

        //exercise
        int id = gameService.addGame("Not in Seeds");

        //verify
        verify(tx, times(1)).beginWrite();
        verify(tx, times(1)).commit();
        verify(tx, never()).rollback();
        assertEquals(fakeId, id);
    }

    @Test
    public void testRemoveGame(){
        //setup
        int fakeId = 999;

        //exercise
        gameService.removeGame(fakeId);

        //verify
        verify(tx, times(1)).beginWrite();
        verify(tx, times(1)).commit();
        verify(tx, never()).rollback();
    }
}
