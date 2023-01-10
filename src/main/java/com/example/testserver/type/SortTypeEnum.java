package com.example.testserver.type;

import lombok.Getter;

@Getter
public enum SortTypeEnum {
    최신순("createdAt"),
    마감임박순("DeadLine"),
    참여자수("Participant"),
    달성률("achievement");

    private String column;
    SortTypeEnum(String column){
        this.column = column;
    }
}
