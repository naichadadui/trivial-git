package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.TrivialApplication;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TrivialApplication.class, MockServletContext.class})
public class GameControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Test
    public void enterRoom() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/game/enterRoom";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("roomId", "1").accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result,"{\"returnMessage\":\"success\",\"enterResult\":0}");
    }

}