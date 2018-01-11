package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void if_user_is_registered_then_register_result_is_negative_one() throws Exception {
        int registerResult = userService.register("cqh","635520704@qq.com","12345");
        Assert.assertEquals(-1, registerResult);
    }

    @Test
    public void if_user_registered_success_then_register_result_is_4() throws Exception {
        int registerResult = userService.register("444","444","4444");
        Assert.assertEquals(4, registerResult);
    }

    @Test
    public void if_user_does_not_exist_then_login_result_is_negative_one() throws Exception {
        int loginResult = userService.login("empty","empty");
        Assert.assertEquals(-1, loginResult);
    }

    @Test
    public void if_user_login_success_then_login_result_is_user_id() throws Exception {
        int loginResult = userService.login("635520704@qq.com","12345");
        Assert.assertEquals(1, loginResult);
    }

    @Test
    public void if_user_enter_wrong_password_then_login_result_is_zero() throws Exception {
        int loginResult = userService.login("635520704@qq.com","wrong");
        Assert.assertEquals(0, loginResult);
    }



}