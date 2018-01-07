package com.ecnu.trivial.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AdminLog {
    private Integer logId;

    private Integer adminId;

    private Date submitTime;

    private Integer actionType;//其实是问题id

    public AdminLog(Integer adminId, Date submitTime, Integer actionType) {
        this.adminId = adminId;
        this.submitTime = submitTime;
        this.actionType = actionType;
    }
}
