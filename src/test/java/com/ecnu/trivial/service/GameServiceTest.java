package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.dto.Game;
import com.ecnu.trivial.dto.Player;
import com.ecnu.trivial.service.impl.GameServiceImpl;
import com.ecnu.trivial.vo.UserVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
@WebAppConfiguration
public class GameServiceTest {
    @Autowired
    private GameService gameService = null;
    private Game game;
    private Player player;
    //private UserVo uservo;

    @Before
    public void initialize(){
        //Arrange
        gameService = new GameServiceImpl();
        //game = new Game(1);
        //player = new Player(uservo.getName(), uservo);
    }

    @Test
    public void the_enterState_is_true_after_user_enter_the_room() throws Exception {
        boolean result = gameService.enterRoom(1,1);
        Assert.assertTrue("enter succ!",result);
    }

    @Test
    public void the_game_can_be_start_after_two_person_prepare(){
        //Act
        gameService.enterRoom(1,1);
        gameService.enterRoom(2,1);
        //gameService.ready(1,1);
        gameService.ready(2,1);
        gameService.start(1);
        //Assert
        Assert.assertEquals("game started successfully!",1,game.getStatus());
    }

    @Test
    public void the_place_should_be_1_if_the_player_moves_forward_1_step() {
        // Act
        player.moveForwardSteps(1);

        // Assert
        assertEquals(1, player.getPlace());
    }


}