package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.service.UserService;
import com.ecnu.trivial.util.ObjectParse;
import com.ecnu.trivial.vo.UserVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
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

    @Override
    public List<UserVo> searchUserBySearchKey(String searchKey){
        List<User> userList = userMapper.searchUsersBySearchKey(searchKey);
        List<UserVo> userVos = userList.stream().map(ObjectParse::parse).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public List<UserVo> allUserInfoList(int adminId,int pageNumber,int pageSize){
        List<User> userList = userMapper.selectAllUsersByPage(new RowBounds(pageNumber*pageSize,pageSize));
        List<UserVo> userVos = userList.stream().map(ObjectParse::parse).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public List<UserVo> getAllUsersOrderByScoreByPage(int pageNumber,int pageSize){
        List<User> userList = userMapper.selectAllUsersOrderByScoreByPage(new RowBounds(pageNumber*pageSize,pageSize));
        List<UserVo> userVos = userList.stream().map(ObjectParse::parse).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public List<User> getAllUsersOrderByScore(){
        List<User> userList = userMapper.selectAllUsersOrderByScore();
        return userList;
    }
}
