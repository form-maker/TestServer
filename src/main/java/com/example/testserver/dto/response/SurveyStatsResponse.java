package com.example.testserver.dto.response;

import com.example.testserver.type.StatusTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SurveyStatsResponse {
    private Integer totalParticipant;
    private Integer totalQuestion;
    private String surveyTitle;
    private String surveySummary;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private LocalDate createdAt;
    private StatusTypeEnum status;
    private Integer achievement;
    private float achievementRate;
    private DailyStatsResponse dailyParticipantList;
    private List<QuestionStatsResponse> questionStatsList;
}
