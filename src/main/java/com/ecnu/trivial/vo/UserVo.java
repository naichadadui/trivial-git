package com.ecnu.trivial.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo {
    private Integer userId;

    private String email;

    private String name;

    private Integer score;

    private double winRate;

    public UserVo(Integer userId, String email, String name, Integer score, double winRate) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.score = score;
        this.winRate = winRate;
    }
}
