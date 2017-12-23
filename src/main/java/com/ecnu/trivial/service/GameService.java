package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;

public interface GameService {
    public boolean enterRoom(User user, int roomId);
    public void ready(User user,int roomId);
    public void dice(int roomId);
    public Questions showQuestion(int roomId);
    public int answerQuestions(int roomId, String answer);
}
