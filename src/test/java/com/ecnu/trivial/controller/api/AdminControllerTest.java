package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.TrivialApplication;
import org.junit.Assert;
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
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void login() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/admin/login";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "635520704@qq.com").param("password", "lalalala").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String no_admin = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(no_admin, "{\"adminId\":-1,\"returnMessage\":\"没有该用户\"}");

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "admin").param("password", "lalalala").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String wrong_pwd = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(wrong_pwd, "{\"adminId\":0,\"returnMessage\":\"密码错误\"}");

        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "admin").param("password", "admin").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String success = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(success, "{\"adminId\":1,\"returnMessage\":\"登录成功\"}");

    }


    @Test
    public void register() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/admin/register";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("nickname", "admin").param("email", "admin").param("password", "lalalala").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String has_admin = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(has_admin, "{\"adminId\":-1,\"returnMessage\":\"该邮箱已被注册\"}");

    }

    @Test
    public void searchAdminLogBySearchKey() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/admin/searchAdminLogBySearchKey";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchId", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String has_admin = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(has_admin, "{\"searchAdminLogs\":\"[]\",\"maxPageNumber\":0}");
    }

    @Test
    public void getAdminLogBySearchKeyByPageNumber() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/admin/getAdminLogBySearchKeyByPageNumber";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchId", "1").param("pageNumber", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String has_admin = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(has_admin, "{\"searchAdminLogs\":\"[]\"}");
    }
}
