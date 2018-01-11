package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.vo.AdminLogVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void if_admin_does_not_exist_then_login_result_is_negative_one() throws Exception {
        int loginResult = adminService.login("empty","empty");
        Assert.assertEquals(-1, loginResult);
    }

    @Test
    public void if_admin_login_success_then_login_result_is_user_id() throws Exception {
        int loginResult = adminService.login("admin","admin");
        Assert.assertEquals(1, loginResult);
    }

    @Test
    public void if_admin_enter_wrong_password_then_login_result_is_zero() throws Exception {
        int loginResult = adminService.login("admin", "wrong");
        Assert.assertEquals(0, loginResult);
    }

    @Test
    public void if_admin_is_registered_then_register_result_is_negative_one() throws Exception {
        int registerResult = adminService.register("admin","admin","12345");
        Assert.assertEquals(-1, registerResult);
    }

    @Test
    public void if_admin_registered_success_then_register_result_is_4() throws Exception {
        int registerResult = adminService.register("444","444","4444");
        Assert.assertEquals(2, registerResult);
    }

    @Test
    public void  getAdminLogsBySearchKeyByPage() throws Exception{
        List<AdminLogVo> adminLogVos=adminService.getAdminLogsBySearchKeyByPage(1,1,10);
        Assert.assertEquals(adminLogVos.size(),0);
    }

    @Test
    public void getMaxPageNumberBySearchKey() throws Exception{
        int pageNum=adminService.getMaxPageNumberBySearchKey(1,10);
        Assert.assertEquals(pageNum,0);
    }
}
