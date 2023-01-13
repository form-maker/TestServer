package com.example.testserver.dto.response;

import com.example.testserver.type.StatusTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyRespnse {
    private Long surveyId; //id
    private String title; //제목
    private String summary; //설명
    private LocalDate startedAt;
    private LocalDate endedAt;
    private LocalDate createdAt;
    private Integer achievement; //달성 인원
    private StatusTypeEnum status;
    private List<Long> questionIdList = new ArrayList<>();
}
