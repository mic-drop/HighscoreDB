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
        Game fakeGame = new Game();
        when(jpaGameDao.findById(fakeId)).thenReturn(fakeGame);

        //exercise
       Game testGame = gameService.getGame(fakeId);

       //verify
        verify(tx, times(1)).beginRead();
        verify(tx, times(1)).commit();
       assertEquals(fakeGame, testGame);

    }

}
