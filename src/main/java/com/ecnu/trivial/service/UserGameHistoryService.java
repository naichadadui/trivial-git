package com.ecnu.trivial.service;

import com.ecnu.trivial.model.UserGameHistory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserGameHistoryService extends BaseService{
    public List<UserGameHistory> getUserGameHistoryByUserId(int userId);
}
