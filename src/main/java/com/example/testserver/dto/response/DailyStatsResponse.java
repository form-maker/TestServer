package com.example.testserver.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DailyStatsResponse {
    private List<LocalDate> date = new ArrayList<>();
    private List<Integer> participant = new ArrayList<>();
}
