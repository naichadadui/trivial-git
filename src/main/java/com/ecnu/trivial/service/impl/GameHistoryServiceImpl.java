package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.GameHistoryMapper;
import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.GameHistory;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.GameHistoryService;
import com.ecnu.trivial.vo.GameHistoryVo;
import com.ecnu.trivial.vo.UserGameHistoryVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<GameHistoryVo> getGameHistoryBySearchKeyByPage(String startTime,String endTime,String winnerName, int pageNumber, int pageSize) {
        List<GameHistory> gameHistories = new ArrayList<>();
        List<GameHistoryVo> gameHistoryVos = new ArrayList<>();
        gameHistories = gameHistoryMapper.selectGameHistoryBySearchKeyByPage(startTime,endTime,winnerName,new RowBounds((pageNumber-1)*pageSize,pageSize));
        gameHistoryVos  = gameHistories.stream().map(this::parse).collect(Collectors.toList());
        gameHistoryVos = gameHistoryVos!=null?gameHistoryVos:(new ArrayList<>());
        return gameHistoryVos;
    }

    @Override
    public int getMaxPageNumberBySearchKey(String startTime,String endTime,String winnerName, int pageSize) {
        int pageNum = 0;
        int gameHistoryNum = gameHistoryMapper.countGameHistory(startTime,endTime,winnerName);
        pageNum = gameHistoryNum/pageSize;
        if(gameHistoryNum%pageSize!=0)
            pageNum+=1;
        return pageNum;
    }

    private GameHistoryVo parse(GameHistory gameHistory){
        GameHistoryVo result = new GameHistoryVo();
        org.springframework.beans.BeanUtils.copyProperties(gameHistory, result);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setStartTimeStr(sdf.format(gameHistory.getStartTime()));
        result.setEndTimeStr(sdf.format(gameHistory.getEndTime()));
        if(gameHistory.getWinnerId()!=null) {
            User winner = getCurrentUser(gameHistory.getWinnerId());
            result.setWinnerName(winner.getName());
        }
        else
            result.setWinnerName("");
        return result;
    }

}
