package com.ecnu.trivial.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AdminLogVo {
    private Integer logId;

    private Integer adminId;

    private Date submitTime;

    private Integer actionType;

    private String adminName;
}
