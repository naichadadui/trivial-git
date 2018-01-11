package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.UserGameHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class UserGameHistoryServiceTest {
    @Autowired
    private UserGameHistoryService userGameHistoryService;

    @Test
    public void enter_user_id_1_should_get_no_user_game_history() throws Exception {
        List<UserGameHistory> userGameHistories = userGameHistoryService.getUserGameHistoryByUserId(1);
        assertEquals(0,userGameHistories.size());
    }
}
