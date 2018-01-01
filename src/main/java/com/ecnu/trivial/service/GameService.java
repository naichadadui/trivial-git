package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.model.User;

import javax.websocket.EncodeException;

public interface GameService extends BaseService{
    boolean enterRoom(int userId, int roomId);
    void ready(int userId,int roomId);
    void prepareToGame(int roomId);
    void start(int roomId);
    void roll(int roomId);
    int answerQuestions(int roomId, String answer) throws EncodeException;
    int leaveRoom(int userId,int roomId);
    void nextTurn(int roomId);
    void outOfPrison(int roomId);
    void notOutOfPrison(int roomId);
}
