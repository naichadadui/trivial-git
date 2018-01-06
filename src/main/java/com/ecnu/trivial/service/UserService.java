package com.ecnu.trivial.service;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;

import java.util.List;

public interface UserService extends BaseService{
    int login(String email,String password);
    int register(String nickname,String email,String password);
    List<UserVo> searchUserBySearchKey(String searchKey);
    List<UserVo> allUserInfoList(int adminId,int pageNumber,int pageSize);
    List<UserVo> getAllUsersOrderByScoreByPage(int pageNumber,int pageSize);
    List<User> getAllUsersOrderByScore();

}
