package com.ecnu.trivial.service.impl;

import com.ecnu.trivial.mapper.QuestionsMapper;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends BaseServiceImpl implements QuestionService{
    @Autowired
    private QuestionsMapper questionsMapper;

    @Override
    public List<Questions> getQuestionsBySearchKeyByPage(String content, String type, int pageNumber, int pageSize) {
        int typeInt = Integer.parseInt(type);
        List<Questions> questions = questionsMapper.selectQuestionsBySearchKeyByPage(content,typeInt,new RowBounds((pageNumber-1)*pageSize,pageSize));
        return questions;
    }

    @Override
    public int getMaxPageNumberBySearchKey(String content,String type,int pageSize){
        int pageNum = 0;
        int typeInt = Integer.parseInt(type);
        int questionCount = questionsMapper.countQuestions(content,typeInt);
        pageNum = questionCount/pageSize;
        if(questionCount%pageSize!=0)
            pageNum+=1;
        return pageNum;
    }

    @Override
    public int deleteQuestions(int[] questionIdArray) {
        int questionCount = 0;
        for(int i = 0;i<questionIdArray.length;i++) {
            questionCount = questionsMapper.deleteByPrimaryKey(questionIdArray[i]);
            questionCount+=questionCount;
        }
        return questionCount;
    }

    @Override
    public int addNewQuestion(Questions questions)
    {
        return questionsMapper.insertSelective(questions);
    }
}
