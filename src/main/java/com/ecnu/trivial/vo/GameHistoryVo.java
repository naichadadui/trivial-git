package com.ecnu.trivial.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class GameHistoryVo {
    private Integer gameId;

    private Date startTime;

    private Date endTime;

    private String startTimeStr;

    private String endTimeStr;

    private Integer winnerId;

    private String winnerName;
}
