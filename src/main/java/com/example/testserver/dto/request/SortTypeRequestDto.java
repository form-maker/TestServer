package com.example.testserver.dto.request;

import com.example.testserver.type.SortTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SortTypeRequestDto {
    private SortTypeEnum sortType;
}
