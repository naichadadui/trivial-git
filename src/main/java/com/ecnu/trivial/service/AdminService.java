package com.ecnu.trivial.service;

import com.ecnu.trivial.model.AdminLog;
import com.ecnu.trivial.vo.AdminLogVo;

import java.util.List;

public interface AdminService extends BaseService {
    int login(String email,String password);
    int register(String nickname,String email,String password);
    List<AdminLogVo> getAdminLogsBySearchKeyByPage(int adminId,int actionType,int pageNumber, int pageSize);
}
