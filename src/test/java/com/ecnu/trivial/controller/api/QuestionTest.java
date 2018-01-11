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

public class QuestionTest {

    @Autowired
    private WebApplicationContext context;

    @Test
    public void searchQuestionsBySearchKey() throws Exception  {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/questions/searchQuestionsBySearchKey";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchContent", "从").param("searchType", "1").accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result,"{\"searchQuestions\":\"[{\\\"id\\\":1,\\\"type\\\":\\\"科学知识\\\",\\\"content\\\":\\\"从抹香鲸体内提炼出的香料是\\\",\\\"trueAns\\\":\\\"龙涎香\\\"}]\",\"maxPageNumber\":1}");
    }

    @Test
    public void searchQuestionsBySearchKeyByPage() throws Exception  {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/questions/searchQuestionsBySearchKeyByPage";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("searchContent", "从").param("searchType", "1").param("pageNumber", "1").accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(result,"{\"searchQuestions\":\"[{\\\"id\\\":1,\\\"type\\\":\\\"科学知识\\\",\\\"content\\\":\\\"从抹香鲸体内提炼出的香料是\\\",\\\"trueAns\\\":\\\"龙涎香\\\"}]\"}");
    }

    @Test
    public void deleteQuestionsById() throws Exception  {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/questions/deleteQuestionsById";
        int[] arrayIds={1,2};
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).param("questionIdArray", arrayIds.toString()).accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void addQuestions() throws Exception  {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        String uri = "/api/questions/addQuestions";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("content","啦啦啊").param("trueAns","A")
                .param("wrongAn1","B").param("wrongAn2","B")
                .param("wrongAn3","B").param("type","2")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String result=mvcResult.getResponse().getContentAsString();
    }
}
