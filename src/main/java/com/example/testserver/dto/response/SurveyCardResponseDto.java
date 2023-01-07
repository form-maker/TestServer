package com.example.testserver.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SurveyCardResponseDto {
    private Long surveyId; //id
    private String title; //제목
    private String summary; //설명
    private Integer achievement; //달성 인원
    private Integer participant;
    private LocalDate deadLine;
    private Integer dDay;
    private LocalDateTime createdAt;
    private Boolean isDone;
}
