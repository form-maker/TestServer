package com.example.testserver;

public enum SortTypeEnum {
    최신순("createdAt"),
    마감임박순("DeadLine"),
    참여자수("Participant"),
    달성률("achievement");

    private String fieldName;
    SortTypeEnum(String fieldName){
        this.fieldName = fieldName;
    }
}
