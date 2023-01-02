package com.example.testserver.dto.response;

import com.example.testserver.AnswerTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerResponseDto {
    private Long answerId;
    private Integer answerNum;
    private AnswerTypeEnum answerType;
    private String answerText;
    private String answerImg;

}
