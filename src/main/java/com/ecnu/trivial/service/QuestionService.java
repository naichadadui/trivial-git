package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.vo.QuestionsVo;

import java.util.List;

public interface QuestionService extends BaseService{
    List<QuestionsVo> getQuestionsBySearchKeyByPage(String content, int type, int pageNumber, int pageSize);
    int getMaxPageNumberBySearchKey(String content,int type,int pageSize);
    int deleteQuestions(int[] questionIdArray,int adminId);
    //int addNewQuestion(Questions questions);
}
