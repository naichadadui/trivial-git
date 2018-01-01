package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;

/**
 * Created by benwu on 14-5-28.
 */
public class Player {
    public static final int MAX_NUMBER_OF_PLACE = 20;//地图总格数（这里还要修改）
    public static final int CATEGORY_POP_1 = 0;
    public static final int CATEGORY_POP_2 = 4;
    public static final int CATEGORY_POP_3 = 8;
    public static final int CATEGORY_SCIENCE_1 = 1;
    public static final int CATEGORY_SCIENCE_2 = 5;
    public static final int CATEGORY_SCIENCE_3 = 9;
    public static final int CATEGORY_SPORTS_1 = 2;
    public static final int CATEGORY_SPORTS_2 = 6;
    public static final int CATEGORY_SPORTS_3 = 10;
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";

    private String playerName;
    private int place = 0;//玩家的位置
    private int sumOfGoldCoins = 0;//玩家本局游戏获得金币数
    private boolean isInPenaltyBox = false;//玩家是否在禁闭室内
    private boolean isReady = false;// 玩家是否同意游戏开始
    private UserVo user; //绑定Player和User

    public Player(String playerName,UserVo user) {
        this.playerName = playerName;
        this.user = user;
    }

    /*根据骰子点数玩家前进相应步数*/
    public void moveForwardSteps(int steps) {
        this.place += steps;
        if (this.place > MAX_NUMBER_OF_PLACE - 1) this.place -= MAX_NUMBER_OF_PLACE;
    }

    public String getCurrentCategory() {
        if (this.place == CATEGORY_POP_1) return POP;
        if (this.place == CATEGORY_POP_2) return POP;
        if (this.place == CATEGORY_POP_3) return POP;
        if (this.place == CATEGORY_SCIENCE_1) return SCIENCE;
        if (this.place == CATEGORY_SCIENCE_2) return SCIENCE;
        if (this.place == CATEGORY_SCIENCE_3) return SCIENCE;
        if (this.place == CATEGORY_SPORTS_1) return SPORTS;
        if (this.place == CATEGORY_SPORTS_2) return SPORTS;
        if (this.place == CATEGORY_SPORTS_3) return SPORTS;
        return ROCK;
    }

    public void winAGoldCoin() {
        this.sumOfGoldCoins++;
    }

    public int countGoldCoins() {
        return this.sumOfGoldCoins;
    }

    public boolean isInPenaltyBox() {
        return this.isInPenaltyBox;
    }

    public void getOutOfPenaltyBox() {
        this.isInPenaltyBox = false;
    }

    public void sentToPenaltyBox() {
        this.isInPenaltyBox = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPlace() {
        return this.place;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public int getSumOfGoldCoins() {
        return sumOfGoldCoins;
    }

    public void setSumOfGoldCoins(int sumOfGoldCoins) {
        this.sumOfGoldCoins = sumOfGoldCoins;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
