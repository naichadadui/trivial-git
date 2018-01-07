package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.vo.UserVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.EncodeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hp on 2018/1/7.
 */
public class GameTest {
    private Game game = null;
    private boolean isGameStillInProgress = true;
    boolean isAllPlayerReady = false;
    List<Questions> totalQuestionList = null;

    @Before
    public void initialize(){
        game = new Game(1);
        UserVo userVo;
        try {
            userVo = new UserVo(1,"1111@qq.com","nzj",0,0);
            game.addNewPlayer(userVo);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        isGameStillInProgress = true;
        totalQuestionList = new ArrayList<>();
        for(int i = 1;i<=50;i++){
            Questions questions = new Questions(0,String.valueOf(i),String.valueOf(i),"11","111","111");
            totalQuestionList.add(questions);
        }
        game.initialQuestions(totalQuestionList);
    }

    @Test
    public void player_number_should_be_2_if_another_user_add_to_game() throws Exception {
        //Act
        int beforeCount = 0;
        if(game.getPlayers()!=null){
            beforeCount = game.getPlayers().size();
        }

        UserVo userVo = new UserVo(2,"2222@qq.com","cqh",0,0);
        game.addNewPlayer(userVo);

        int expctedAfter = beforeCount+1;

        //Assert
        assertEquals("players number is 2!",expctedAfter,game.getPlayers().size());
    }


    @Test
    public void all_players_are_ready_if_all_players_set_ready(){
        // Act

        try {
            for(int i = 0;i<game.getPlayers().size();i++){
                game.setReady(game.getPlayers().get(i).getUser().getUserId());
            }
            isAllPlayerReady = game.isAllPlayerReady();

        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertTrue("All player are ready",isAllPlayerReady);
    }

    @Test
    public void if_game_start_then_game_process_should_be_reset(){
        // Act
        try {
            game.startGame(new Date());
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertTrue("current player id is 0",game.getGameProcess().getCurrentPlayerId()==0);
        assertTrue("game status is 1",game.getGameProcess().getStatus()==1);
        assertTrue("current player number is 2",game.getGameProcess().getPlayers().size()==2);
    }

    @Test
    public void the_game_should_be_over_if_a_player_rolls_the_dice_and_answers_each_question_correctly_for_6_times() {
        // Act
        for (int i = 0; i < 6; i++) {
            try {
                game.setCurrentPlayerId(0);
                game.roll();
                isGameStillInProgress = game.answeredCorrect();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }

        // Assert
        assertFalse(isGameStillInProgress);
    }

    @Test
    public void the_game_should_be_over_if_a_player_rolls_the_dice_for_7_times_and_answers_the_question_wrongly_for_1_time_followed_by_a_rolling_number_which_is_not_even_but_then_correctly_for_6_times() {
        // Act
        try {
            game.setCurrentPlayerId(0);
            game.roll();
            game.answeredWrong();
            game.setRollNumber(5);
            game.setCurrentPlayerId(0);
            game.roll();
            for (int i = 0; i < 6; i++) {
                game.setCurrentPlayerId(0);
                game.roll();
                isGameStillInProgress = game.answeredCorrect();
            }
        } catch (EncodeException e) {
            e.printStackTrace();
           }

        // Assert
        assertFalse(isGameStillInProgress);
    }

    @Test
    public void the_game_should_be_over_if_a_player_rolls_the_dice_for_8_times_and_answers_the_question_wrongly_for_1_time_followed_by_a_rolling_number_which_is_even_then_followed_by_a_rolling_number_which_is_not_even_and_then_correctly_for_6_times() {
        // Act
        try {
            game.setCurrentPlayerId(0);
            game.roll();
            game.answeredWrong();
            game.setRollNumber(5);
            game.setCurrentPlayerId(0);
            game.roll();
            game.setRollNumber(4);
            game.setCurrentPlayerId(0);
            game.roll();
            for (int i = 0; i < 6; i++) {
                game.setCurrentPlayerId(0);
                game.roll();
                isGameStillInProgress = game.answeredCorrect();
            }
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        // Assert
        assertFalse(isGameStillInProgress);
    }

    @Test
    public void the_player_place_should_be_moved_by_5_if_player_rolls_5_and_the_player_is_not_in_plenty_box() {
        // Act
        int beforePlace = 0;
        int exceptPlace = 0;
        int afterPlace = 0;
        try {
            game.setCurrentPlayerId(0);
            beforePlace = game.getPlayers().get(game.getCurrentPlayerId()).getPlace();
            game.setRollNumber(5);
            game.roll();
            exceptPlace = beforePlace + 5;
            if (exceptPlace > 16 - 1)
                exceptPlace -= 16;
            afterPlace = game.getPlayers().get(game.getCurrentPlayerId()).getPlace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(afterPlace,exceptPlace);
    }
}