package com.example.testserver.dto.response;

import com.example.testserver.type.QuestionTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponseDto {
    private Long questionId;
    private QuestionTypeEnum questionType;
    private Integer questionNum;
    private Integer volume;
    private String questionTitle;
    private String questionSummary;
    private List<AnswerResponseDto> answerList;
}
