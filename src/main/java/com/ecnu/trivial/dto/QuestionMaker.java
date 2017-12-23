package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
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

    private List<Questions> popQuestionList = new LinkedList<>();
    private List<Questions> scienceQuestionList = new LinkedList<>();
    private List<Questions> sportsQuestionList = new LinkedList<>();
    private List<Questions> rockQuestionList = new LinkedList<>();

    public QuestionMaker(List<Questions> popQuestionList,List<Questions> scienceQuestionList,List<Questions> sportsQuestionList,List<Questions> rockQuestionList){
        this.popQuestionList = popQuestionList;
        this.scienceQuestionList = scienceQuestionList;
        this.sportsQuestionList = sportsQuestionList;
        this.rockQuestionList = rockQuestionList;
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

    public List<Questions> getPopQuestionList() {
        return popQuestionList;
    }

    public void setPopQuestionList(List<Questions> popQuestionList) {
        this.popQuestionList = popQuestionList;
    }

    public List<Questions> getScienceQuestionList() {
        return scienceQuestionList;
    }

    public void setScienceQuestionList(List<Questions> scienceQuestionList) {
        this.scienceQuestionList = scienceQuestionList;
    }

    public List<Questions> getSportsQuestionList() {
        return sportsQuestionList;
    }

    public void setSportsQuestionList(List<Questions> sportsQuestionList) {
        this.sportsQuestionList = sportsQuestionList;
    }

    public List<Questions> getRockQuestionList() {
        return rockQuestionList;
    }

    public void setRockQuestionList(List<Questions> rockQuestionList) {
        this.rockQuestionList = rockQuestionList;
    }
}
