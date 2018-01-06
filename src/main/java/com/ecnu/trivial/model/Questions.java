package com.ecnu.trivial.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Questions {
    private Integer questionId;

    private Integer type;

    private String content;

    private String trueAns;

    private String falseAns1;

    private String falseAns2;

    private String falseAns3;

    public Questions(Integer type, String content, String trueAns, String falseAns1, String falseAns2, String falseAns3) {
        this.type = type;
        this.content = content;
        this.trueAns = trueAns;
        this.falseAns1 = falseAns1;
        this.falseAns2 = falseAns2;
        this.falseAns3 = falseAns3;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTrueAns() {
        return trueAns;
    }

    public void setTrueAns(String trueAns) {
        this.trueAns = trueAns == null ? null : trueAns.trim();
    }

    public String getFalseAns1() {
        return falseAns1;
    }

    public void setFalseAns1(String falseAns1) {
        this.falseAns1 = falseAns1 == null ? null : falseAns1.trim();
    }

    public String getFalseAns2() {
        return falseAns2;
    }

    public void setFalseAns2(String falseAns2) {
        this.falseAns2 = falseAns2 == null ? null : falseAns2.trim();
    }

    public String getFalseAns3() {
        return falseAns3;
    }

    public void setFalseAns3(String falseAns3) {
        this.falseAns3 = falseAns3 == null ? null : falseAns3.trim();
    }
}