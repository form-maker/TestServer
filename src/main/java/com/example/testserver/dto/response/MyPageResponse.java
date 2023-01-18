package com.example.testserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponse {
    private String username;
    DataPageResponse pageData;
}
