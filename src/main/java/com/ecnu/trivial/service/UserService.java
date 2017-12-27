package com.ecnu.trivial.service;

import com.ecnu.trivial.model.User;

import java.util.List;

public interface UserService extends BaseService{
    public int login(String email,String password);
    public int register(String nickname,String email,String password);
    public List<User> getAllUsersOrderByScore();
}
