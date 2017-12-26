package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;

public interface GameService extends BaseService{
    public boolean enterRoom(int userId, int roomId);
    public void ready(int userId,int roomId);
    public void dice(int roomId);
    public Questions showQuestion(int roomId);
    public int answerQuestions(int roomId, String answer);
}
