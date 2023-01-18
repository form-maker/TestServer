package com.example.testserver;


import com.example.testserver.dto.request.*;
import com.example.testserver.dto.response.*;
import com.example.testserver.exception.CustomException;
import com.example.testserver.exception.ErrorCode;
import com.example.testserver.type.AnswerTypeEnum;
import com.example.testserver.type.QuestionTypeEnum;
import com.example.testserver.type.SortTypeEnum;
import com.example.testserver.type.StatusTypeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseEntity<ResponseMessage>getMainList(SortTypeEnum sortBy,  int page, int size){
        List<SurveyCardResponseDto> mainList = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy.getColumn());
        Pageable pageable = PageRequest.of(page-1, size, sort);
        SurveyCardResponseDto survey;
        for(int i = 0; i < 100; i++){
            LocalDate startedAt = LocalDate.now().minusDays(i%30);
            LocalDate endedAt = LocalDate.now().plusDays(i%31);
            int dDay = Period.between(LocalDate.from(startedAt), endedAt).getDays();
            survey = SurveyCardResponseDto.builder()
                    .surveyId((long)i)
                    .title("설문 제목 부분입니다 "+ i)
                    .summary("설문에 대한 설명입니다. 대충 이이이러한 설문입니다. " + i)
                    .startedAt(startedAt)
                    .endedAt(endedAt)
                    .dDay(dDay)
                    .totalTime(Math.round((8*20)/60f))
                    .totalQuestion(8)
                    .participant((i+10)%34 + (i*3)%10)
                    .createdAt(startedAt.minusDays(i%3))
                    .build();
            mainList.add(survey);
        }

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), mainList.size());

        Page<SurveyCardResponseDto> surveyPage = new PageImpl<>(mainList.subList(start, end), pageable, mainList.size());

        DataPageResponse<SurveyCardResponseDto> response = new DataPageResponse<>(surveyPage);

        ResponseMessage responseMessage = new ResponseMessage("조회 성공", 200, response);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 생성")
    @PostMapping("/survey")
    public ResponseEntity<ResponseMessage>createSurvey(@RequestBody SurveyCreateRequestDto requestDto){

        ResponseMessage responseMessage = new ResponseMessage("설문 생성", 200, requestDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "전체 조회(설문 생성자 입장)")
    @GetMapping("/survey/my-page")
    public ResponseEntity<ResponseMessage>getSurveyList(SortTypeEnum sortBy, int page, int size, StatusTypeEnum status){
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy.getColumn());
        Pageable pageable = PageRequest.of(page-1, size, sort);



        List<SurveyCardResponseDto> mainList = new ArrayList<>();
        SurveyCardResponseDto survey;
        for(int i = 1; i < 10; i++){
            LocalDate startedAt = LocalDate.now().minusDays(i%30);
            LocalDate endedAt = LocalDate.now().plusDays(i%31);
            int dDay = Period.between(LocalDate.from(startedAt), endedAt).getDays();
            int achievement = 30*i%50;
            int participant = (i+10)%34 + (i*3)%10;
            survey = SurveyCardResponseDto.builder()
                    .surveyId((long)i)
                    .title("설문 제목 부분입니다 "+ i)
                    .summary("설문에 대한 설명입니다. 대충 이이이러한 설문입니다. " + i)
                    .startedAt(startedAt)
                    .endedAt(endedAt)
                    .dDay(dDay)
                    .participant(participant)
                    .achievement(achievement)
                    .totalQuestion(8)
                    .achievementRate(achievement/participant)
                    .createdAt(startedAt.minusDays(i%3))
                    .status(status)
                    .build();

            mainList.add(survey);
        }
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), mainList.size());

        Page<SurveyCardResponseDto> surveyPage = new PageImpl<>(mainList.subList(start, end), pageable, mainList.size());

        DataPageResponse<SurveyCardResponseDto> dataPageResponse = new DataPageResponse<>(surveyPage);
        MyPageResponse response = new MyPageResponse("범준", dataPageResponse);

        ResponseMessage responseMessage = new ResponseMessage("", 200, response);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 수정")
    @PutMapping("/survey/{questionId}")
    public ResponseEntity<ResponseMessage>updateSurvey(@RequestBody QuestionCreateRequestDto requestDto, @PathVariable Long questionId){
        ResponseMessage responseMessage = new ResponseMessage("설문 수정 성공", 200, requestDto);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "설문 삭제")
    @DeleteMapping("/survey/{surveyId}")
    public ResponseEntity<ResponseMessage>deleteSurvey(@PathVariable Long surveyId){
        ResponseMessage responseMessage = new ResponseMessage("설문 삭제 성공", 200, surveyId);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "설문 문제 조회(1문제씩)")
    @GetMapping("/question")
    public ResponseEntity<ResponseMessage>getQuestion(@RequestParam Long questionId){
        List<AnswerResponseDto> answerList = new ArrayList<>();

        Integer questionNum = (int)(questionId%8);
        QuestionTypeEnum questionType = switch (questionNum) {
            case 1 -> QuestionTypeEnum.MULTIPLE_CHOICE;
            case 2 -> QuestionTypeEnum.SINGLE_CHOICE;
            case 3 -> QuestionTypeEnum.SLIDE;
            case 4 -> QuestionTypeEnum.RANK;
            case 5 -> QuestionTypeEnum.SHORT_DESCRIPTIVE;
            case 6 -> QuestionTypeEnum.LONG_DESCRIPTIVE;
            case 7 -> QuestionTypeEnum.STAR;
            case 0 -> QuestionTypeEnum.SCORE;
            default -> null;
        };
        AnswerResponseDto answer;
        for(int i = 0; i < questionType.getAnswerLen(); i++){
            answer = new AnswerResponseDto( i, AnswerTypeEnum.TEXT, "문항"+i);
            answerList.add(answer);
        }


        QuestionResponseDto questionResponseDto = new QuestionResponseDto(questionId, questionType, questionNum, questionType.getVolume(), questionType.getQuestionTitle(), "설문 질문에 대한 설명입니다.", answerList);
        ResponseMessage responseMessage = new ResponseMessage("설문 조회 성공", 200, questionResponseDto );
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }
    @ApiOperation(value = "설문 조회")
    @GetMapping("/survey")
    public ResponseEntity<ResponseMessage> getSurvey(@RequestParam Long surveyId){
        List<Long> questionIdList = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L);
        SurveyResponse surveyResponse = new SurveyResponse(surveyId, "설문 제목", "설문에 대한 설명입니다.", LocalDate.now().minusDays(3), LocalDate.now().plusDays(30), LocalDate.now().minusDays(5), 52, StatusTypeEnum.IN_PROCEED, 8, questionIdList);
        ResponseMessage responseMessage = new ResponseMessage("설문 조회 성공", 200, surveyResponse);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }


    @ApiOperation(value = "설문 응답")
    @PostMapping("/survey/{surveyId}/reply")
    public ResponseEntity<ResponseMessage>respondSurvey(@RequestBody List<ReplyRequestDto> requestDtos){
        ResponseMessage responseMessage = new ResponseMessage("설문 응답 성공", 200, requestDtos);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }

    @ApiOperation(value = "설문 통계")
    @GetMapping("/survey/stats")
    public ResponseEntity<ResponseMessage>getStats(@RequestParam Long surveyId, @RequestParam String start, @RequestParam String end){
        LocalDate startedAt;
        LocalDate endedAt;
        try{
            startedAt = LocalDate.parse(start);
            endedAt = LocalDate.parse(end);
        }catch (DateTimeParseException e){
            startedAt = LocalDate.MIN;
            endedAt = LocalDate.MAX;
        }
        QuestionStatsResponse multipleChoice = QuestionStatsResponse.builder()
                .questionNum(1)
                .questionType(QuestionTypeEnum.MULTIPLE_CHOICE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.MULTIPLE_CHOICE + ")")
                .questionSummary("대충 그럴싸한 MULTIPLE_CHOICE에 대한 설명")
                .selectList(Arrays.asList(new SelectResponse("문항1", 59.2f), new SelectResponse("문항2", 30.4f), new SelectResponse("문항3", 10.4f)))
                .build();
        QuestionStatsResponse singleChoice = QuestionStatsResponse.builder()
                .questionNum(2)
                .questionType(QuestionTypeEnum.SINGLE_CHOICE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.SINGLE_CHOICE + ")")
                .questionSummary("대충 그럴싸한 SINGLE_CHOICE에 대한 설명")
                .selectList(Arrays.asList(new SelectResponse("문항1", 19.6f), new SelectResponse("문항2", 30.2f), new SelectResponse("문항3", 20.2f)))
                .build();
        QuestionStatsResponse slide = QuestionStatsResponse.builder()
                .questionNum(3)
                .questionType(QuestionTypeEnum.SLIDE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.SLIDE + ")")
                .leftLabel("매우 도움 됨")
                .rightLabel("전혀 도움이 되지 않음")
                .questionSummary("대충 그럴싸한 SLIDE에 대한 설명")
                .volume(3)
                .satisfactionList(Arrays.asList(5.2f, 9.2f, 5.1f, 32.1f, 20.3f, 20.1f, 8f))
                .build();
        QuestionStatsResponse rank = QuestionStatsResponse.builder()
                .questionNum(4)
                .questionType(QuestionTypeEnum.RANK)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.RANK + ")")
                .questionSummary("대충 그럴싸한 RANK에 대한 설명")
                .selectList(Arrays.asList(new SelectResponse("문항1", Arrays.asList(39.5f, 20.2f, 5.3f)), new SelectResponse("문항2", Arrays.asList(30.5f, 29.2f, 5.3f)), new SelectResponse("문항3", Arrays.asList(5.3f, 20.2f, 39.5f))))
                .build();
        QuestionStatsResponse shortDescriptive = QuestionStatsResponse.builder()
                .questionNum(5)
                .questionType(QuestionTypeEnum.SHORT_DESCRIPTIVE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.SHORT_DESCRIPTIVE + ")")
                .questionSummary("대충 그럴싸한 SHORT_DESCRIPTIVE에 대한 설명")
                .selectList(Arrays.asList(new SelectResponse("사수", 56), new SelectResponse("이름", 5), new SelectResponse("연봉", 40), new SelectResponse("돈", 42), new SelectResponse("사람", 23)))
                .build();
        QuestionStatsResponse longDescriptive = QuestionStatsResponse.builder()
                .questionNum(6)
                .questionType(QuestionTypeEnum.LONG_DESCRIPTIVE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.LONG_DESCRIPTIVE + ")")
                .questionSummary("대충 그럴싸한 LONG_DESCRIPTIVE에 대한 설명")
                .selectList(Arrays.asList(new SelectResponse("사수", 56), new SelectResponse("이름", 5), new SelectResponse("연봉", 40), new SelectResponse("돈", 42), new SelectResponse("사람", 23)))
                .build();
        QuestionStatsResponse star = QuestionStatsResponse.builder()
                .questionNum(7)
                .questionType(QuestionTypeEnum.STAR)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.STAR + ")")
                .questionSummary("대충 그럴싸한 STAR에 대한 설명")
                .questionAvg(3.8f)
                .satisfactionList(Arrays.asList(9.2f, 10.3f, 40.1f, 20.3f, 20.1f))
                .build();
        QuestionStatsResponse score = QuestionStatsResponse.builder()
                .questionNum(8)
                .questionType(QuestionTypeEnum.SCORE)
                .questionTitle("대충 그럴싸한 제목(" + QuestionTypeEnum.SCORE + ")")
                .questionSummary("대충 그럴싸한 SCORE에 대한 설명")
                .questionAvg(3.8f)
                .satisfactionList(Arrays.asList(9.2f, 10.3f, 40.1f, 20.3f, 20.1f))
                .build();
        DailyStatsResponse dailyStatsList = new DailyStatsResponse();
        int temp = (int)ChronoUnit.DAYS.between(LocalDate.of(2023, 1, 13), LocalDate.of(2023, 3, 2));
        for(int i = 0; i < temp; i++){
            dailyStatsList.getDate().add(LocalDate.now().minusDays(temp-i));
            dailyStatsList.getParticipant().add((10+i%23));
        }
        int total= dailyStatsList.getParticipant().stream().reduce(0, Integer::sum);
        System.out.println(total);
        SurveyStatsResponse surveyStatsResponse = SurveyStatsResponse.builder()
                .totalParticipant(total)
                .totalQuestion(8)

                .surveyTitle("설문 제목입니다.")
                .surveySummary("설문에 대한 설명입니다.")
                .startedAt(LocalDate.of(2023, 1, 13))
                .endedAt(LocalDate.of(2023, 3, 2))
                .createdAt(LocalDate.now().minusDays(32))
                .status(StatusTypeEnum.DONE)
                .achievement(200)
                .achievementRate((total/(total+56f))*100)
                .dailyParticipantList(dailyStatsList)
                .questionStatsList(Arrays.asList(multipleChoice, singleChoice, slide, rank, shortDescriptive, longDescriptive, star, score))
                .build();
        ResponseMessage responseMessage = new ResponseMessage("설문 응답 성공", 200, surveyStatsResponse);
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }
}
