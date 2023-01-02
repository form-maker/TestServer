package com.example.testserver.dto.request;

import com.example.testserver.AnswerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class AnswerRequestDto {
    private Integer answerNum;
    private AnswerTypeEnum answerType; //enum 형태에 따라 AnswerData를 저장하면 될듯
    private String answerText;
    private String answerImg;
}
