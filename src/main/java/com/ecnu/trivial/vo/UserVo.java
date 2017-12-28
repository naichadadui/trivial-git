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
}
