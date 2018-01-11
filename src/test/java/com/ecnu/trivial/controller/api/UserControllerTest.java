package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.Map;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TrivialApplication.class, MockServletContext.class})

public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void login() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/login";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("email", "1").param("password", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status1 = Integer.valueOf(mvcResult.getResponse().getStatus()).intValue();

        Assert.assertEquals(status1, 200);
    }

    @Test
    public void register() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/register";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("nickname", "1").param("email", "1").param("password", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status1 = Integer.valueOf(mvcResult.getResponse().getStatus()).intValue();

        Assert.assertEquals(status1, 200);
    }

    @Test
    public void searchUserBySearchKey() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/homepage/searchUserBySearchKey";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchName", "a").param("searchEmail", "").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();

    }

}