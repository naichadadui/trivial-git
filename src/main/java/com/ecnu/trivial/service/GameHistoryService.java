package com.ecnu.trivial.service;

import com.ecnu.trivial.model.GameHistory;

import java.util.List;

public interface GameHistoryService {
    public List<GameHistory> getLatestTwoGames();
}
