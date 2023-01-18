package com.example.testserver.dto.response;

import com.example.testserver.type.QuestionTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionStatsResponse {
    private Integer questionNum;
    private QuestionTypeEnum questionType;
    private String questionTitle;
    private Integer volume;
    private String questionSummary;
    private String leftLabel;
    private String rightLabel;
    private float questionAvg;
    private List<Float> satisfactionList;
    private List<SelectResponse> selectList;
}
