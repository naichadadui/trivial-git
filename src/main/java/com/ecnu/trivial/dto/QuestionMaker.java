package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.vo.QuestionVo;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by benwu on 14-5-28.
 */

@NoArgsConstructor
public class QuestionMaker {
    public static final int MAX_NUMBER_OF_QUESTIONS = 50;
//    private LinkedList<String> popQuestions = new LinkedList<String>();
//    private LinkedList<String> scienceQuestions = new LinkedList<String>();
//    private LinkedList<String> sportsQuestions = new LinkedList<String>();
//    private LinkedList<String> rockQuestions = new LinkedList<String>();

    private List<Questions> totalQuestionList = new LinkedList<>();
//    private List<Questions> popQuestionList = new LinkedList<>();
//    private List<Questions> scienceQuestionList = new LinkedList<>();
//    private List<Questions> sportsQuestionList = new LinkedList<>();
//    private List<Questions> rockQuestionList = new LinkedList<>();
//
//    public QuestionMaker(List<Questions> popQuestionList,List<Questions> scienceQuestionList,List<Questions> sportsQuestionList,List<Questions> rockQuestionList){
//        this.popQuestionList = popQuestionList;
//        this.scienceQuestionList = scienceQuestionList;
//        this.sportsQuestionList = sportsQuestionList;
//        this.rockQuestionList = rockQuestionList;
//    }

    public QuestionMaker(List<Questions> totalQuestionList){
        this.totalQuestionList = totalQuestionList;
    }

//    public QuestionMaker() {
//        for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
//            addPopQuestion("Pop Question " + i);
//            addScienceQuestion(("Science Question " + i));
//            addSportsQuestion(("Sports Question " + i));
//            addRockQuestion("Rock Question " + i);
//        }
//    }
//
//    public void addPopQuestion(String popQuestion) {
//        popQuestions.add(popQuestion);
//    }
//
//    public void addScienceQuestion(String scienceQuestion) {
//        scienceQuestions.add(scienceQuestion);
//    }
//
//    public void addSportsQuestion(String sportsQuestion) {
//        sportsQuestions.add(sportsQuestion);
//    }
//
//    public void addRockQuestion(String rockQuestion) {
//        rockQuestions.add(rockQuestion);
//    }
//
//    public String removeFirstPopQuestion() {
//        return popQuestions.removeFirst();
//    }
//
//    public String removeFirstScienceQuestion() {
//        return scienceQuestions.removeFirst();
//    }
//
//    public String removeFirstSportsQuestion() {
//        return sportsQuestions.removeFirst();
//    }
//
//    public String removeFirstRockQuestion() {
//        return rockQuestions.removeFirst();
//    }
    public Questions getFirstQuestion(){
        if(totalQuestionList.size()>0) {
            Questions questions = totalQuestionList.get(0);
            totalQuestionList.remove(0);
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
