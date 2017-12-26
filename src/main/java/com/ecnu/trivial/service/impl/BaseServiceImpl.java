package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl implements BaseService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getCurrentUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
