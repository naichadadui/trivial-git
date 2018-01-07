package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.AdminMapper;
import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.Admin;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public User getCurrentUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Admin getCurrentAdmin(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }
}
