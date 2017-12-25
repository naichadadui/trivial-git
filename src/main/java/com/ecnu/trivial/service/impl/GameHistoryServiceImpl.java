package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.GameHistoryMapper;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.service.GameHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {
    @Autowired
    private GameHistoryMapper gameHistoryMapper;
    @Override
    public List<GameHistory> getLatestTwoGames() {
        return gameHistoryMapper.getLatestTwoGames();
    }
}
