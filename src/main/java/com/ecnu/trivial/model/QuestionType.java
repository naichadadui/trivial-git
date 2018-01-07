package com.ecnu.trivial.model;

public enum QuestionType {
    ALL(0,"全部"),
    SCIENCE(1, "科学知识"),
    MUSIC(2, "流行音乐"),
    SPORTS(3, "体育知识"),
    COMPUTER(4, "计算机知识"),
    OTHERS(5,"其他");



    QuestionType(int value, String typeStr) {
        this.value = value;
        this.typeStr = typeStr;
    }

    public Integer getValue() {
        return value;
    }


    public static QuestionType valueOf(int value) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.value == value) {
                return questionType;
            }
        }
        return OTHERS;
    }

    private final int value;
    private final String typeStr;

    public String getTypeStr() {
        return typeStr;
    }
}
