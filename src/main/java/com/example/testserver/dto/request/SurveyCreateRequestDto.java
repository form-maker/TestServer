package com.example.testserver.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SurveyCreateRequestDto {
    private String title;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private Integer achievement;
    private String summary;
    private List<QuestionCreateRequestDto> questionList;
}
