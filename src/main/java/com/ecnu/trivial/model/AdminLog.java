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

    private Integer actionType;

}
