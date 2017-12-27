package com.ecnu.trivial.vo;

import com.ecnu.trivial.model.GameHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserGameHistoryVo {
    private Integer gameId;

    private Integer userId;

    private Integer score;

    private GameHistoryVo gameHistoryVo;
}
