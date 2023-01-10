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
    private Integer questionNum;
    private Integer choice;
    private Integer value; //choice와 합쳐도 될듯
    private String descriptive;
    private List<Integer> rank;
}
