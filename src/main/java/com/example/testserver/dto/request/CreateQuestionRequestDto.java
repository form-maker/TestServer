package com.example.testserver.dto.request;

import com.example.testserver.QuestionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateQuestionRequestDto {
    private QuestionTypeEnum questionType;
    private Integer questionNum;
    private Integer minValue;
    private Integer maxValue;
    private String questionTitle;
    private List<AnswerRequestDto> answerList;

}
