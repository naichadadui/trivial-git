package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void login() throws Exception {
        int loginResult = userService.login("111","111");
        Assert.assertEquals(1, loginResult);
    }

    @Test
    public void register() throws Exception {
    }

}