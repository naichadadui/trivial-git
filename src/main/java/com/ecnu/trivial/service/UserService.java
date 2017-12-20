package com.ecnu.trivial.service;

import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int login(String email,String password){
        int loginResult = 0;
        List<User> userList = new ArrayList<>();
        userList = userMapper.selectByEmail(email);
        if (userList.size() == 0) {
            /*不存在该用户，返回-1*/
            loginResult = -1;
        }
        else {
            /*找到该用户*/
            User user = userList.get(0);
            if (user.getPassword().equals(password)) {
                /*若密码正确则返回该用户userId*/
                loginResult = user.getUserId();
            }
            else{
                /*若密码错误则返回0*/
                loginResult = 0;
            }
        }
        return loginResult;
    }


    public int register(String nickname,String email,String password){
        int registerResult = 0;
        List<User> userList = userMapper.selectByEmail(email);
        if (userList.size() != 0) {
            /*该邮箱已经被注册，返回-1*/
            registerResult = -1;
        }
        else {
            /*该邮箱可以被注册*/
            User user = new User();
            user.setEmail(email);
            user.setName(nickname);
            user.setPassword(password);
            registerResult = userMapper.insertSelective(user);
            if(registerResult!=0)
                /*注册成功，返回用户userId*/
                registerResult = user.getUserId();
        }
        return registerResult;
    }
}
