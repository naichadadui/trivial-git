package com.ecnu.trivial.model;

public enum QuestionType {
    OTHERS(0,"其他"),
    SCIENCE(1, "科学"),
    POP(2, "流行音乐");


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
