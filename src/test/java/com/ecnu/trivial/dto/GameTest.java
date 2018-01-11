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
        game.getCurrentPlayerId();
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

        int expectedAfter = beforeCount+1;

        //Assert
        assertEquals("players number is 2!",expectedAfter,game.getPlayers().size());
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
    public void first_player_set_ready_and_new_player_enter_room_does_not_ready_then_not_all_players_ready(){
        // Act
        try {
            game.setReady(game.getPlayers().get(0).getUser().getUserId());
            UserVo userVo = new UserVo(2,"2222@qq.com","cqh",0,0);
            game.addNewPlayer(userVo);
            isAllPlayerReady = game.isAllPlayerReady();

        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertFalse("Not all player are ready",isAllPlayerReady);
    }

    @Test
    public void three_new_players_enter_room_then_this_room_is_full(){
        //Before
        boolean isFullPlayer = game.isFullPlayer();
        assertFalse("The room is not full.",isFullPlayer);
        // Act
        try {
            UserVo userVo1 = new UserVo(2,"2222@qq.com","2",0,0);
            game.addNewPlayer(userVo1);
            UserVo userVo2 = new UserVo(3,"3333@qq.com","3",0,0);
            game.addNewPlayer(userVo2);
            UserVo userVo3 = new UserVo(4,"4444@qq.com","4",0,0);
            game.addNewPlayer(userVo3);
            isFullPlayer = game.isFullPlayer();

        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertTrue("The room is full.",isFullPlayer);
    }

    @Test
    public void game_prepare_to_game_then_game_status_should_be_initialized(){
        // Act
        try {
            game.prepareToGame();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertTrue("action type is room to game",game.getActionType().equals("room to game"));
        assertTrue("current player id is 0",game.getGameProcess().getCurrentPlayerId()==0);
        assertTrue("game status is 1",game.getGameProcess().getStatus()==1);
        assertTrue("current player number is 1",game.getGameProcess().getPlayers().size()==1);
    }

    @Test
    public void if_game_start_then_game_process_should_be_reset(){
        //Before
        assertEquals(false,game.isGameStart());
        // Act
        try {
            game.startGame(new Date());
        } catch (EncodeException e) {
            e.printStackTrace();
        }
        // Assert
        assertTrue("current player id is 0",game.getGameProcess().getCurrentPlayerId()==0);
        assertTrue("game status is 1",game.getGameProcess().getStatus()==1);
        assertTrue("current player number is 1",game.getGameProcess().getPlayers().size()==1);
        assertEquals(true,game.isGameStart());
    }

    @Test
    public void after_one_player_answer_correct_current_player_id_should_be_set_to_0_and_process_was_refresh() {
        // Act
        try {
            for(int i =0;i<game.getPlayers().size();i++)
                game.setReady(game.getPlayers().get(i).getUser().getUserId());
            game.startGame(new Date());
            game.answeredCorrect();
            game.prepareNextDiceAndNextQuestion();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(0,game.getCurrentPlayerId());
        assertEquals(1,game.getStatus());
        assertEquals("startGame",game.getActionType());

    }

    @Test
    public void if_player_send_a_right_answer_then_player_should_not_be_sent_to_pelenty_box() {
        // Act
        boolean isAnswerRight = false;
        try {
            String answer = "1";
            String trueAns = totalQuestionList.get(0).getTrueAns();
            game.startGame(new Date());
            isAnswerRight = game.answerQuestion(answer,trueAns);
        } catch (EncodeException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(0,game.getCurrentPlayerId());
        assertEquals(1,game.getStatus());
        assertEquals("startGame",game.getActionType());

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
            game.setRollNumber(4);
            game.setCurrentPlayerId(0);
            game.roll();
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