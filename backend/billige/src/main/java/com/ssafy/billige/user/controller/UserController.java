package com.ssafy.billige.user.controller;

import com.ssafy.billige.user.dto.request.UserSignupRequest;
import com.ssafy.billige.user.dto.response.BasicResponse;
import com.ssafy.billige.user.dto.response.UserProfileResponse;
import com.ssafy.billige.user.service.UserService;
import com.ssafy.billige.utils.S3UploadUtils;
import com.ssafy.billige.utils.TokenUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.ssafy.billige.utils.StringUtils.AUTH_HEADER;
import static com.ssafy.billige.utils.StringUtils.USER;

@RestController
@RequestMapping(value = USER)
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @PostMapping("/signup/email-check")
    @ApiOperation(value = "email 중복체크")
    public Object emailCheck(@RequestBody Map<String, String> request){
        String userEmail = request.get("userEmail");
        return ResponseEntity.ok().body(userService.emailCheck(userEmail));

    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public Object signup( @RequestBody UserSignupRequest userSignupRequest){

        final BasicResponse result = new BasicResponse();
        HttpStatus status = HttpStatus.CONFLICT;

        if(userService.emailCheck(userSignupRequest.getUserEmail())){
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
        HttpStatus status = HttpStatus.OK;

        String requestEmail = request.get("userEmail");
        logger.info(requestEmail + " : request email certification");
        String key = userService.certificateEmail(requestEmail);
        result.data = "success";
        result.status = true;
        result.object = key;
        logger.info(requestEmail + " : send email success");
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

    @Autowired
    S3UploadUtils s3UploadUtils;

    @PutMapping("/modify/profile")
    @ApiOperation(value = "프로필 편집")
    public Object modifyProfile(@RequestHeader(AUTH_HEADER) String token, @RequestParam(required = false, value = "userPassword") String requestPassword, @RequestParam(required = false, value = "userImage")MultipartFile multipartFile){

        Claims claims = TokenUtils.getClaimsFromToken(token);
        String tokenEmail = (String) claims.get("userEmail");
        String userSet = (String) claims.get("userName") + "(" + tokenEmail + ")";

        logger.info(tokenEmail + " : request modify profile");
        String imageUrl = null;

        // 프로필사진이 업데이트 된 경우 S3에 사진저장하고 url 받아오기
        if(multipartFile != null){
            try {
                imageUrl = s3UploadUtils.upload(multipartFile, "profile", userSet);
                logger.info(tokenEmail + " : profile image upload s3 success");
            } catch (IOException e){
                logger.info(tokenEmail + " : profile image upload s3 fail");
                e.printStackTrace();
            }
        }

        userService.modifyProfile(tokenEmail, requestPassword, imageUrl);
        logger.info(tokenEmail + " : modify profile success");
        return ResponseEntity.ok().body("success");
    }

    @PutMapping("/modify/address")
    @ApiOperation(value = "위치 변경")
    public Object modifyAddress(@RequestHeader(AUTH_HEADER) String token, @RequestBody Map<String, String> request){

        Claims claims = TokenUtils.getClaimsFromToken(token);
        String tokenEmail = (String) claims.get("userEmail");
        logger.info(tokenEmail + " : request modify address");

        userService.modifyAddress(tokenEmail, request);
        logger.info(tokenEmail + " : modify address success");
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/create/wallet")
    @ApiOperation(value = "지갑 생성")
    public Object createWallet(@RequestHeader(AUTH_HEADER) String token, @RequestBody Map<String, String> request){

        Claims claims = TokenUtils.getClaimsFromToken(token);
        String tokenEmail = (String) claims.get("userEmail");
        String userWallet = request.get("userWallet");
        logger.info(tokenEmail + " : request create wallet");

        userService.createWallet(tokenEmail, userWallet);
        logger.info(tokenEmail + " : create wallet success");
        return ResponseEntity.ok().body("success");
    }

    @PutMapping("/charge/wallet")
    @ApiOperation(value = "빌리 충전")
    public Object chargeBli(@RequestHeader(AUTH_HEADER) String token, @RequestBody Map<String, Integer> request){

        Claims claims = TokenUtils.getClaimsFromToken(token);
        String tokenEmail = (String) claims.get("userEmail");
        int bli = request.get("bli");
        logger.info(tokenEmail + " : request charge bli");

        userService.chargeBli(tokenEmail, bli);
        logger.info(tokenEmail + " : charge bli success");
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/mypage")
    @ApiOperation(value = "마이페이지 보기")
    public Object showProfile(@RequestHeader(AUTH_HEADER) String token){

        Claims claims = TokenUtils.getClaimsFromToken(token);
        String tokenEmail = (String) claims.get("userEmail");
        logger.info(tokenEmail + " : request show profile");

        UserProfileResponse result = userService.showProfile(tokenEmail);
        logger.info(tokenEmail + " : show profile success");
        return ResponseEntity.ok().body(result);

    }
}
