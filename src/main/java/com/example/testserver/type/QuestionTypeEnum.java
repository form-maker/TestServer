package com.example.testserver.type;

import lombok.Getter;

@Getter
public enum QuestionTypeEnum {
    MULTIPLE_CHOICE(null, "제목(객관식 다중 선택)", 5),
    SINGLE_CHOICE(null, "제목(객관식 단일 선택)", 5),
    SLIDE(5, "제목(슬라이드)", 0),
    RANK(null, "제목(순위)", 5),
    SHORT_DESCRIPTIVE(null, "제목(주관식 단답형)", 0),
    LONG_DESCRIPTIVE(null, "제목(주관식 장문형)", 0),
    STAR(5, "제목(별점)", 0),
    SCORE(5, "제목(별점)", 0);

    private Integer volume;
    private String questionTitle;
    private int answerLen;

    QuestionTypeEnum(Integer volume, String questionTitle, int answerLen){
        this.volume = volume;
        this.questionTitle = questionTitle;
        this.answerLen = answerLen;

    }
}
