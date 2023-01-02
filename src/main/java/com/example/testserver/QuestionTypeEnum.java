package com.example.testserver;

import lombok.Getter;

import java.awt.*;

@Getter
public enum QuestionTypeEnum {
    MultipleChoice(null, null, "제목(객관식 다중 선택)", 5),
    SingleChoice(null, null, "제목(객관식 단일 선택)", 5),
    Slide(0, 5, "제목(슬라이드)", 0),
    Rank(null, null, "제목(순위)", 5),
    ShortDescriptive(null, null, "제목(주관식 단답형)", 0),
    LongDescriptive(null, null, "제목(주관식 장문형)", 0),
    Star(0, 7, "제목(별점)", 0);

    private Integer minValue;
    private Integer maxValue;
    private String questionTitle;
    private int answerLen;

    QuestionTypeEnum(Integer minValue, Integer maxValue, String questionTitle, int answerLen){
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.questionTitle = questionTitle;
        this.answerLen = answerLen;

    }
}
