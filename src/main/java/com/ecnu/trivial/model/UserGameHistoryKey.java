package com.ecnu.trivial.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserGameHistoryKey {
    private Integer gameId;

    private Integer userId;

    public UserGameHistoryKey(Integer gameId, Integer userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}