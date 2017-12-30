package com.ecnu.trivial.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionVo
{
    Integer questionId;
    String content;
    String[] options;
}
