package com.ecnu.trivial.dto;

import com.ecnu.trivial.mapper.QuestionsMapper;
import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.service.GameService;
import com.ecnu.trivial.vo.QuestionVo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by benwu on 14-5-28.
 */

@NoArgsConstructor
public class QuestionMaker {
    public static final int MAX_NUMBER_OF_QUESTIONS = 50;

    private List<Questions> totalQuestionList = new LinkedList<>();

    public QuestionMaker(List<Questions> totalQuestionList){
        this.totalQuestionList = totalQuestionList;
    }

    public Questions getFirstQuestion(){
        if(totalQuestionList.size()>0) {
            Questions questions = totalQuestionList.get(0);
            totalQuestionList.remove(0);
            totalQuestionList.add(questions);
            return questions;
        }
        return null;
    }

    public List<Questions> getTotalQuestionList() {
        return totalQuestionList;
    }

    public void setTotalQuestionList(List<Questions> totalQuestionList) {
        this.totalQuestionList = totalQuestionList;
    }
}
