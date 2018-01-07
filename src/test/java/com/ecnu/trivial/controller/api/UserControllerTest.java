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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
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
        //RequestBuilder e = get("/login").param
        String expectedResult = "hello world!";
        String uri = "/show";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("错误，正确的返回值为200", status == 200);

    }

    @Test
    public void register() throws Exception {

    }

}