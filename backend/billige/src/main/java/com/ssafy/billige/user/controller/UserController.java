package com.ssafy.billige.user.controller;

import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.dto.response.BasicResponse;
import com.ssafy.billige.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ssafy.billige.utils.StringUtils.USER;

@RestController
@RequestMapping(value = USER)
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @PostMapping("/signup/email-check")
    @ApiOperation(value = "email 중복체크")
    public boolean emailCheck(@RequestParam String userEmail){
        return userService.emailCheck(userEmail);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public Object signup( @RequestBody UserSignupRequest userSignupRequest){

        final BasicResponse result = new BasicResponse();
        HttpStatus status = HttpStatus.CONFLICT;

        if(emailCheck(userSignupRequest.getUserEmail())){
            // 사용가능한 email 인 경우
            int signupResult = userService.signup(userSignupRequest);

            if(signupResult == 1){
                result.data = "success";
                result.status = true;
                status = HttpStatus.OK;
            }
        }else{
            // email 중복인 경우
            result.data = "email overlap";
            result.status = false;
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/email-certification")
    @ApiOperation("email 인증받기")
    public Object certificateEmail(@RequestBody Map<String, String> request){

        final BasicResponse result = new BasicResponse();
        HttpStatus status = HttpStatus.NOT_FOUND;

        String requestEmail = request.get("userEmail");
        logger.info(requestEmail + " : request email certification");
        if(!emailCheck(requestEmail)){
            // email 중복 : 존재하는 회원인 경우
            String key = userService.certificateEmail(requestEmail);
            if(key.equals(null)){
                // email 전송 오류
                result.data = "send email failed";
                result.status = false;
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                logger.error(requestEmail + " : send email failed");
            }else{
                result.data = "success";
                result.status = true;
                result.object = key;
                status = HttpStatus.OK;
                logger.info(requestEmail + " : send email success");
            }
        }else{
            result.data = "non-existent user";
            result.status = false;
        }
        return new ResponseEntity<>(result, status);
    }

    @PutMapping("/modify/password")
    @ApiOperation(value = "비밀번호 수정")
    public Object modifyPassword(@RequestBody Map<String, String> modifyRequest){
        logger.info(modifyRequest.get("userEmail") + " : request modify password");
        userService.modifyPassword(modifyRequest);
        logger.info(modifyRequest.get("userEmail") + " : modify password success");
        return ResponseEntity.ok().body("success");
    }
}
