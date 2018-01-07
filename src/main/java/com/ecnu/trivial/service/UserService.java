package com.ecnu.trivial.service;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;

import java.util.List;

public interface UserService extends BaseService{
    int login(String email,String password);
    int register(String nickname,String email,String password);

    List<UserVo> searchUserBySearchKeyByPage(String name,String email,int pageNum,int pageSize);

    int getMaxPageNumberBySearchKey(String name,String email,int pageSize);

    List<User> getAllUsersOrderByScore();

}
