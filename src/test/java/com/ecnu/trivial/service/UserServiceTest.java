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
    public void login() throws Exception {
        int loginResult = userService.login("635520704@qq.com","12345");
        Assert.assertEquals(1, loginResult);
    }

    @Test
    public void register() throws Exception {
        int registerResult = userService.register("cqh","635520704@qq.com","12345");
        Assert.assertEquals(-1, registerResult);
    }

}