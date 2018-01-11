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
public class GameHistoryTest  {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void searchGameRecordBySearchKey() throws Exception{
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/gameRecord/searchGameRecordBySearchKey";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("startTime", "2018-01-02 01:28:00").param("endTime", "2018-01-02 01:29:42").param("winnerName","").accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result,"{\"maxPageNumber\":1,\"searchGameHistory\":\"[{\\\"id\\\":10,\\\"startTime\\\":\\\"2018-01-02 01:28:00\\\",\\\"endTime\\\":\\\"2018-01-02 01:29:42\\\",\\\"winner\\\":\\\"cqh\\\"}]\"}");
    }

    @Test
    public void getAdminLogByPageNumber() throws Exception{
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/gameRecord/getGameRecordBySearchKeyByPageNumber";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("startTime", "2018-01-02 01:28:00").param("endTime", "2018-01-02 01:29:42")
                .param("winnerName","").param("pageNumber","1").accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result,"{\"searchGameHistory\":\"[{\\\"id\\\":10,\\\"startTime\\\":\\\"2018-01-02 01:28:00\\\",\\\"endTime\\\":\\\"2018-01-02 01:29:42\\\",\\\"winner\\\":\\\"cqh\\\"}]\"}");
    }
}
