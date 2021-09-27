package com.ssafy.billige.user.controller;

import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.dto.response.UserSignupResponse;
import com.ssafy.billige.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.billige.utils.StringUtils.USER;

@RestController
@RequestMapping(value = USER)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup/email-check")
    public boolean emailCheck(@RequestParam String userEmail){
        return userService.emailCheck(userEmail);
    }

    @PostMapping("/signup")
    public Object signup( @RequestBody UserSignupRequest userSignupRequest){

        final UserSignupResponse result = new UserSignupResponse();
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
}
