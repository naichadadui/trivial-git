package com.ecnu.trivial.dto;

import com.ecnu.trivial.vo.UserVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.PrimitiveIterator;

import static org.junit.Assert.*;

/**
 * Created by hp on 2018/1/7.
 */
public class GameTest {
    @Autowired
    private Game game;
    //private User user;
    private UserVo userVo;
    private GameProcess gameProcess;

    @Before
    public void initialize(){
        game = new Game();
        userVo = new UserVo(1,"1111@qq.com","nzj",null,0);
    }
    
    @Test
    public void after_isAllPlayerReady_the_state_is_false(){
        //Act
        boolean state = game.isAllPlayerReady();
        //Assert
        Assert.assertTrue("AllPlayerReady!",state);
    }

    @Test
    public void after_addNewPlayer_the_palyers_number_will_be_more() throws Exception {
        //Act
        int beforeCount=0;
        if(game.getPlayers()!=null){
            beforeCount = game.getPlayers().size();
        }

        game.addNewPlayer(userVo);
        int expctedAfter = beforeCount+1;

        //Assert
        Assert.assertEquals("players number ++!",expctedAfter,game.getPlayers().size());
    }

}