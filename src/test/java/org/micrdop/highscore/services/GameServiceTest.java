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
    private JpaGameDao jpaGameDao;

    @Before
    public void setup() {
        jpaGameDao = mock(JpaGameDao.class);

        gameService = new GameService();
        gameService.setJpaGameDao(jpaGameDao);
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
        assertEquals(fakeId, id);
    }

    @Test
    public void testRemoveGame(){
        //setup
        int fakeId = 999;

        //exercise
        gameService.removeGame(fakeId);

        //verify
        verify(jpaGameDao, times(1)).delete(fakeId);
    }
}
