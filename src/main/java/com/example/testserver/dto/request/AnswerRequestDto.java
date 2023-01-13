package com.example.testserver.dto.request;

import com.example.testserver.type.AnswerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class AnswerRequestDto {
    private Integer answerNum;
    private AnswerTypeEnum answerType;
    private String answerValue;
}
