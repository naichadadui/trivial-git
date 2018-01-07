package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.AdminLogMapper;
import com.ecnu.trivial.mapper.AdminMapper;
import com.ecnu.trivial.model.Admin;
import com.ecnu.trivial.model.AdminLog;
import com.ecnu.trivial.service.AdminService;
import com.ecnu.trivial.util.ObjectParse;
import com.ecnu.trivial.vo.AdminLogVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminLogMapper adminLogMapper;

    public int login(String email,String password){
        int loginResult = 0;
        List<Admin> adminList = adminMapper.selectAdminByEmail(email);
        if (adminList.size() == 0) {
            /*不存在该用户，返回-1*/
            loginResult = -1;
        }
        else {
            /*找到该用户*/
            Admin admin = adminList.get(0);
            if (admin.getPassword().equals(password)) {
                /*若密码正确则返回该管理员adminId*/
                loginResult = admin.getAdminId();
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
        List<Admin> userList = adminMapper.selectAdminByEmail(email);
        if (userList.size() != 0) {
            /*该邮箱已经被注册，返回-1*/
            registerResult = -1;
        }
        else {
            /*该邮箱可以被注册*/
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setName(nickname);
            admin.setPassword(password);
            registerResult = adminMapper.insertSelective(admin);
            if(registerResult!=0)
                /*注册成功，返回管理员adminId*/
                registerResult = admin.getAdminId();
        }
        return registerResult;
    }

    @Override
    public List<AdminLogVo> getAdminLogsBySearchKeyByPage(int adminId,int pageNumber, int pageSize) {
        List<AdminLog> adminLogs = adminLogMapper.selectAdminLogsByPage(adminId,new RowBounds((pageNumber-1)*pageSize,pageSize));
        List<AdminLogVo> adminLogVos  = adminLogs.stream().map(this::parse).collect(Collectors.toList());
        return adminLogVos;
    }

    private AdminLogVo parse(AdminLog adminLog){
        AdminLogVo adminLogVo = new AdminLogVo();
        Admin admin = adminMapper.selectByPrimaryKey(adminLog.getAdminId());
        try {
            BeanUtils.copyProperties(adminLogVo, adminLog);
            adminLogVo.setAdminName(admin.getName());
        } catch (Exception e) {

        }
        return adminLogVo;
    }

    @Override
    public int getMaxPageNumberBySearchKey(int adminId,int pageSize){
        int pageNum = 0;
        int adminLogNumber = adminLogMapper.countAdminLogs(adminId);
        pageNum = adminLogNumber/pageSize;
        if(adminLogNumber%pageSize!=0)
            pageNum+=1;
        return pageNum;
    }
}
