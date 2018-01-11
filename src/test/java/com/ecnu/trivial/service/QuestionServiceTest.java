package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.vo.QuestionsVo;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @Test
    public void getQuestionsBySearchKeyByPage()throws Exception{
        List<QuestionsVo> questionsVos=questionService.getQuestionsBySearchKeyByPage("从",1,1,10);
        Assert.assertEquals(questionsVos.size(),1);
    }
    @Test
    public void getMaxPageNumberBySearchKey()throws Exception{
        int pageNum=questionService.getMaxPageNumberBySearchKey("从",1,10);
        Assert.assertEquals(pageNum,0);
    }
    @Test
    public void deleteQuestions() throws Exception{
        int[] arrays={1,2};
        int questionCount=questionService.deleteQuestions(arrays,1);
        Assert.assertEquals(questionCount,2);
    }
    @Test
    public void addNewQuestion() throws Exception{
        Questions questions=new Questions(1,"A","A","A","A","A");
        int result=questionService.addNewQuestion(questions);
        Assert.assertEquals(result,1);
    }

}
