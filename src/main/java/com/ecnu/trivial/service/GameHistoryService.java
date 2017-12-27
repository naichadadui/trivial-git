package com.ecnu.trivial.service;

import com.ecnu.trivial.model.GameHistory;

import java.util.List;

public interface GameHistoryService extends BaseService{
    public List<GameHistory> getLatestTwoGames();
    public GameHistory selectByPrimaryKey(int gameId);
}
