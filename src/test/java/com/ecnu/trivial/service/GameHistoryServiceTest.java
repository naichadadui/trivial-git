package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.vo.GameHistoryVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class GameHistoryServiceTest {
    @Autowired
    private GameHistoryService gameHistoryService;

    @Test
    public void if_user_is_registered_then_register_result_is_negative_one() throws Exception {
        List<GameHistory> latestTwoGames = gameHistoryService.getLatestTwoGames();
        assertEquals(10,latestTwoGames.get(0).getGameId().intValue());
        assertEquals(9,latestTwoGames.get(1).getGameId().intValue());
        assertEquals(2, latestTwoGames.size());
    }

    @Test
    public void return_game_history_id_is_1_by_enter_id_1() throws Exception {
        int gameId = 1;
        GameHistory gameHistory = gameHistoryService.selectByPrimaryKey(gameId);
        assertEquals(gameId, gameHistory.getGameId().intValue());
    }

    @Test
    public void if_search_key_is_empty_then_should_return_all_game_history_page_1() throws Exception {
        List<GameHistoryVo> gameHistoryList = gameHistoryService.getGameHistoryBySearchKeyByPage("","","",1,10);
        assertEquals(8,gameHistoryList.size());
    }

    @Test
    public void if_search_key_is_empty_then_should_return_all_game_history_page_2_is_empty() throws Exception {
        List<GameHistoryVo> gameHistoryList = gameHistoryService.getGameHistoryBySearchKeyByPage("","","",2,10);
        assertEquals(0,gameHistoryList.size());
    }

    @Test
    public void if_search_winner_is_user_1_then_should_return_max_page_number_is_1() throws Exception {
        int maxPage = gameHistoryService.getMaxPageNumberBySearchKey("","","cqh",10);
        assertEquals(1,maxPage);
    }


}
