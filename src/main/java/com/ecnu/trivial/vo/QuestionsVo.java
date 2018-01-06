package com.ecnu.trivial.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionsVo {
    private Integer questionId;

    private Integer type;

    private String content;

    private String trueAns;

    private String falseAns1;

    private String falseAns2;

    private String falseAns3;

    private String typeStr;
}
