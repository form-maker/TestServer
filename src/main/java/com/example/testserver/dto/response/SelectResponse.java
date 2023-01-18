package com.example.testserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SelectResponse {
    private String answer;
    private float value;
    private List<Float> rankList;

    public SelectResponse(String answer, float value) {
        this.answer = answer;
        this.value = value;
    }

    public SelectResponse(String answer, List<Float> rankList) {
        this.answer = answer;
        this.rankList = rankList;
    }
}
