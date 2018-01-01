package com.ecnu.trivial.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserGameHistory extends UserGameHistoryKey {
    private Integer score;

    public UserGameHistory(Integer gameId,Integer userId,Integer score){
        super(gameId,userId);
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}