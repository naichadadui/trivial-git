package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.Questions;
import com.ecnu.trivial.vo.QuestionVo;

import java.util.List;

public class GameProcess {
    private String actionType;
    private int roomId;
    private List<Player> players;
    private QuestionVo currentQuestion;
    private int currentPlayerId;
    private int status;//0:等待中 1:游戏中
    private int dice;
    private Player winner;
    private int rollNumber;
    private boolean isRight;

    public GameProcess(Game game){
        this.actionType = "room";
        this.roomId = game.getRoomId();
        this.players = game.getPlayers();
        this.currentPlayerId = game.getCurrentPlayerId();
        this.status = game.getStatus();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public QuestionVo getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionVo currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(int currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
