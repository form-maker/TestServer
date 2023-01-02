package com.example.testserver;


import com.example.testserver.dto.request.*;
import com.example.testserver.dto.response.*;
import com.example.testserver.exception.CustomException;
import com.example.testserver.exception.ErrorCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private final String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VySW5mbyIsImlkIjoidGVzdDEiLCJleHAiOjE2NzIwMjIzODAsImlhdCI6MTY3MjAxODc4MH0.MEIwNx1_X2fXC-";
    private final String header = "Authorization";

    @ApiOperation(value = "회원가입")
    @PostMapping("/user/signup")
    public ResponseEntity<ResponseMessage> signup (@RequestBody SignupRequestDto requestDto) {
        if(requestDto.getLoginId().equals("test1")){
            throw new CustomException(ErrorCode.DUPLICATE_LOGINID);
        }
        if(requestDto.getUsername().equals("범준")){
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        ResponseMessage responseMessage = new ResponseMessage("회원가입이 완료되었습니다.", 200, requestDto);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @ApiOperation(value = "loginId 중복 확인")
    @GetMapping("/user/signup/loginId")
    public ResponseEntity<ResponseMessage>  loginIdCheck (@RequestParam String loginId){
        if(loginId.equals("test1")){
            throw new CustomException(ErrorCode.DUPLICATE_LOGINID);
        }
        ResponseMessage responseMessage = new ResponseMessage("사용가능한 LoginId입니다.", 200, loginId);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "username 중복 확인")
    @GetMapping("/user/signup/username")
    public ResponseEntity<ResponseMessage> usernameCheck(@RequestParam String username){
        if(username.equals("범준")){
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        ResponseMessage responseMessage = new ResponseMessage("사용가능한 Username입니다.", 200, username);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/user/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        if(!requestDto.getLoginId().equals("test1")){
            throw new CustomException(ErrorCode.INCORRECT_USER);
        }
        if(!requestDto.getPassword().equals("qwer1234")){
            throw new CustomException(ErrorCode.INCORRECT_USER);
        }

        response.addHeader(header, token);
        ResponseMessage responseMessage = new ResponseMessage("로그인이 완료되었습니다.", 200, requestDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "토큰 유효 확인")
    @GetMapping("/user")
    public ResponseEntity<ResponseMessage>checkToken(HttpServletRequest request){
        boolean equal= request.getHeader(header).equals(token);
        CheckTokenResponseDto checkTokenResponseDto = new CheckTokenResponseDto(equal);
        ResponseMessage responseMessage = new ResponseMessage("토큰 유효 정보 반환", 200, checkTokenResponseDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "전체 조회(사용자 입장)")
    @GetMapping("/survey/main")
    public ResponseEntity<ResponseMessage>getMainList(){
        List<SurveyCardResponseDto> mainList = new ArrayList<>();
        SurveyCardResponseDto survey;
        for(int i = 0; i < 10; i++){
            survey = new SurveyCardResponseDto((long)i, "이거좀 해주세요"+i, "대충 이런 설문입니다."+i, null, (long)(20-(i*2)), LocalDateTime.now().minusDays(i), null);
            mainList.add(survey);
        }

        ResponseMessage responseMessage = new ResponseMessage("조회 성공", 200, mainList);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 생성")
    @PostMapping("/survey")
    public ResponseEntity<ResponseMessage>createSurvey(@RequestBody SurveyRequestDto requestDto){

        ResponseMessage responseMessage = new ResponseMessage("설문 생성", 200, requestDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "전체 조회(설문 생성자 입장)")
    @GetMapping("/survey/list")
    public ResponseEntity<ResponseMessage>getSurveyList(){
        List<SurveyCardResponseDto> mainList = new ArrayList<>();
        SurveyCardResponseDto survey;
        for(int i = 0; i < 10; i++){
            survey = new SurveyCardResponseDto((long)i, "이거좀 해주세요"+i, "대충 이런 설문입니다."+i, 10+i, (long)(20-(i*2)), LocalDateTime.now().minusDays(i), i%3!=0);
            mainList.add(survey);
        }

        ResponseMessage responseMessage = new ResponseMessage("", 200, mainList);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 수정")
    @PutMapping("/survey/{questionId}")
    public ResponseEntity<ResponseMessage>updateSurvey(@RequestBody CreateQuestionRequestDto requestDto, @PathVariable Long questionId){
        ResponseMessage responseMessage = new ResponseMessage("설문 수정 성공", 200, requestDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "설문 삭제")
    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<ResponseMessage>deleteSurvey(@PathVariable Long surveyId){
        ResponseMessage responseMessage = new ResponseMessage("설문 삭제 성공", 200, surveyId);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "설문 조회(1문제씩)")
    @GetMapping("/survey")
    public ResponseEntity<ResponseMessage>getQuestion(@RequestParam Long surveyId, @RequestParam Long questionId){
        List<AnswerResponseDto> answerList = new ArrayList<>();

        Integer questionNum = (int)(questionId%7);
        System.out.println(questionNum);
        QuestionTypeEnum questionType = switch (questionNum) {
            case 1 -> QuestionTypeEnum.MultipleChoice;
            case 2 -> QuestionTypeEnum.SingleChoice;
            case 3 -> QuestionTypeEnum.Slide;
            case 4 -> QuestionTypeEnum.Rank;
            case 5 -> QuestionTypeEnum.ShortDescriptive;
            case 6 -> QuestionTypeEnum.LongDescriptive;
            case 0 -> QuestionTypeEnum.Star;
            default -> null;
        };
        AnswerResponseDto answer;
        for(int i = 0; i < questionType.getAnswerLen(); i++){
            answer = new AnswerResponseDto((long)i+20, i, questionId%3==0?AnswerTypeEnum.Image:AnswerTypeEnum.Text, questionId%3==0?null:"문항"+i, questionId%3==0?"/img/cat.jpg":null);
            answerList.add(answer);
        }


        QuestionResponseDto questionResponseDto = new QuestionResponseDto(questionId, questionType, questionNum, questionType.getMinValue(), questionType.getMaxValue(), questionType.getQuestionTitle(), answerList);
        ResponseMessage responseMessage = new ResponseMessage("설문 조회 성공", 200, questionResponseDto );
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 응답")
    @PostMapping("/survey/{surveyId}")
    public ResponseEntity<ResponseMessage>respondSurvey(@RequestBody List<RespondQuestionRequestDto> requestDtos){
        ResponseMessage responseMessage = new ResponseMessage("설문 응답 성공", 200, requestDtos);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }
}
