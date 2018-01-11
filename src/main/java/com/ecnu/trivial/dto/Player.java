package com.ecnu.trivial.dto;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;

import java.util.Random;

/**
 * Created by benwu on 14-5-28.
 */
public class Player {
    public static final int MAX_NUMBER_OF_PLACE = 16;//地图最大下标格数

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
        Random rand = new Random();
        this.place = rand.nextInt(15);
        this.isInPenaltyBox = false;
    }

    public void sentToPenaltyBox() {
        this.place = MAX_NUMBER_OF_PLACE;
        this.isInPenaltyBox = true;
    }

    public String getPlayerName() {
        return playerName;
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

    @Override
    public String toString() {
        return this.playerName;
    }
}
