package com.ecnu.trivial.model;

import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class GameHistory {
    private Integer gameId;

    private Date startTime;

    private Date endTime;

    private Integer winnerId;

    public GameHistory(Date startTime,Date endTime,Integer winnerId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.winnerId = winnerId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }
}