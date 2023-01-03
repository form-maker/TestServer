package com.example.testserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER("파라미터 값을 확인해주세요.", 400),
    INVALID_USERNAME_PATTERN("id는 소문자와 숫자 조합 4자리에서 10자리입니다.",400),
    INVALID_PASSWORD_PATTERN("비밀번호는 소문자, 대문자, 숫자, 특수문자(!@#$%^&+=) 조합 8자리에서 15자리입니다.",400),

    INVALID_UPDATE("수정된 데이터가 없습니다.", 400),
    DUPLICATE_LOGINID("중복된 아이디가 존재합니다.", 400),
    DUPLICATE_EMAIL("해당 이메일로 이미 가입된 회원입니다.", 400),
    DUPLICATE_USERNAME("중복된 닉네임이 존재합니다.", 400),
    REQUIRED_ALL("모든 항목이 필수값입니다.",400),
    WRONG_IMAGE_FORMAT("파일을 확인해주세요.", 400),

    //404 NOT_FOUND 잘못된 리소스 접근
    CONTENT_NOT_FOUND("존재하지 않는 게시글 입니다.",400),
    COMMENT_NOT_FOUND("존재하지 않는 댓글 입니다.", 400),
    USERNAME_NOT_FOUND("존재하지 않는 아이디 입니다.",400),
    INCORRECT_USER("일치하는 회원정보가 존재하지 않습니다.",400),
    DUPLICATE_JOIN("이미 초대된 유저입니다.", 400),

    AUTHORIZATION_DELETE_FAIL("삭제 권한이 없습니다.", 400),
    AUTHORIZATION_UPDATE_FAIL("수정 권한이 없습니다.", 400),
    AUTHORIZATION_CREATE_FAIL("생성 권한이 없습니다.", 400),

    INTERNAL_SERVER_ERROR("서버 에러입니다. 서버 팀에 연락주세요!", 500);


    private final String msg;
    private final int statusCode;
}