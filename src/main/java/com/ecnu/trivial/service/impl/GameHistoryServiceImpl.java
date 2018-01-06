package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.GameHistoryMapper;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.vo.UserGameHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameHistoryServiceImpl extends BaseServiceImpl implements GameHistoryService {
    @Autowired
    private GameHistoryMapper gameHistoryMapper;
    @Override
    public List<GameHistory> getLatestTwoGames() {
        List<GameHistory> latestGame = new ArrayList<>();
        latestGame = gameHistoryMapper.getLatestTwoGames();
        return latestGame;
    }

    @Override
    public GameHistory selectByPrimaryKey(int gameId){
        return gameHistoryMapper.selectByPrimaryKey(gameId);
    }

}
