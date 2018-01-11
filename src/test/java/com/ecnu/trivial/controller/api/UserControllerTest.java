package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.mapper.UserMapper;
import com.ecnu.trivial.model.Map;
import com.ecnu.trivial.model.User;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TrivialApplication.class, MockServletContext.class})
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void searchUserBySearchKey() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/searchUserBySearchKey";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchName", "l").param("searchEmail", "").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result, "{\"maxPageNumber\":1,\"searchUsers\":\"[{\\\"id\\\":1,\\\"name\\\":\\\"hlz\\\",\\\"email\\\":\\\"2108889695@qq.com\\\",\\\"score\\\":0},{\\\"id\\\":2,\\\"name\\\":\\\"ll\\\",\\\"email\\\":\\\"hhhhhhhh\\\",\\\"score\\\":0}]\"}");
    }

    @Test
    public void getUsersBySearchKeyByPageNumber() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/getUsersBySearchKeyByPageNumber";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchName", "l").param("searchEmail", "").param("pageNumber", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result, "{\"searchUsers\":\"[{\\\"id\\\":1,\\\"name\\\":\\\"hlz\\\",\\\"email\\\":\\\"2108889695@qq.com\\\",\\\"score\\\":0}]\"}");
    }

    @Test
    public void login() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/login";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "635520704@qq.com").param("password", "lalalala").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status1 = Integer.valueOf(mvcResult.getResponse().getStatus()).intValue();
        String case2_wrongpwd = mvcResult.getResponse().getContentAsString();

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "ooo").param("password", "lalal").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String case1_nouser = mvcResult.getResponse().getContentAsString();

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "635520704@qq.com").param("password", "12345").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String case3_rightuser = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(case3_rightuser, "{\"returnMessage\":\"登录成功\",\"userId\":1}");
        Assert.assertEquals(case2_wrongpwd, "{\"returnMessage\":\"密码错误\",\"userId\":0}");
        Assert.assertEquals(case1_nouser, "{\"returnMessage\":\"没有该用户\",\"userId\":-1}");
        Assert.assertEquals(status1, 200);
    }

    @Test
    public void register() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/register";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("nickname", "ll").param("email", "635520704@qq.com").param("password", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status1 = Integer.valueOf(mvcResult.getResponse().getStatus()).intValue();
        String case_userhaved = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(case_userhaved, "{\"returnMessage\":\"该邮箱已被注册\",\"userId\":-1}");

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("nickname", "ll").param("email", "hhhhhhhh").param("password", "aaaa").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String success = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(success, "{\"returnMessage\":\"注册成功\",\"userId\":4}");
    }
}
