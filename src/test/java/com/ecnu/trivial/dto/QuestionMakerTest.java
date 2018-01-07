package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionMakerTest {
    private QuestionMaker questionMaker = null;
    List<Questions> totalQuestionList = null;

    @Before
    public void initialize() {
        // Arrange
        totalQuestionList = new ArrayList<>();
        for(int i = 1;i<=50;i++){
            Questions questions = new Questions(0,String.valueOf(i),String.valueOf(i),"11","111","111");
            totalQuestionList.add(questions);
        }
        questionMaker = new QuestionMaker(totalQuestionList);
    }

    @Test
    public void the_first_question_could_be_removed_and_added_to_the_last_position_of_question_list() {
        //Act
        Questions beforeQuestions = questionMaker.getTotalQuestionList().get(0);
        questionMaker.getFirstQuestion();
        Questions afterQuestions = questionMaker.getTotalQuestionList().get(49);

        //Assert
        assertEquals("first question was removed and added to last", beforeQuestions, afterQuestions);
    }
}
