package com.example.testserver.dto.request;

import com.example.testserver.type.QuestionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RespondQuestionRequestDto {
    private QuestionTypeEnum questionType;
    private String questionTitle;
    private String questionSummary;
    private Integer value;
    private List<Integer> rank;
}
