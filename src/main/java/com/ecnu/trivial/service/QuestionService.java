package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Questions;

import java.util.List;

public interface QuestionService extends BaseService{
    List<Questions> getQuestionsBySearchKeyByPage(String content, String type, int pageNumber, int pageSize);
    int getMaxPageNumberBySearchKey(String content,String type,int pageSize);
    int deleteQuestions(int[] questionIdArray);
    int addNewQuestion();
}
