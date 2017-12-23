package com.ecnu.trivial.service;

import com.ecnu.trivial.model.User;

public interface UserService {
    public int login(String email,String password);
    public int register(String nickname,String email,String password);
    public User getCurrentUser(Integer userId);
}
