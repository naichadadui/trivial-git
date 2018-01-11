package com.ecnu.trivial.service;

import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.vo.GameHistoryVo;

import java.util.List;

public interface GameHistoryService extends BaseService{
    List<GameHistory> getLatestTwoGames();
    GameHistory selectByPrimaryKey(int gameId);
    List<GameHistoryVo> getGameHistoryBySearchKeyByPage(String startTime,String endTime,String winnerName, int pageNumber, int pageSize);
    int getMaxPageNumberBySearchKey(String startTime,String endTime,String winnerName,int pageSize);
}
