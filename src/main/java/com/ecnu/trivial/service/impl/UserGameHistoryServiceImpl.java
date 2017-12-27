package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.UserGameHistoryMapper;
import com.ecnu.trivial.model.UserGameHistory;
import com.ecnu.trivial.service.UserGameHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameHistoryServiceImpl extends BaseServiceImpl implements UserGameHistoryService {
    @Autowired
    private UserGameHistoryMapper userGameHistoryMapper;

    public List<UserGameHistory> getUserGameHistoryByUserId(int userId){
        return userGameHistoryMapper.selectUserGameHistoryByUserId(userId);
    }
}
