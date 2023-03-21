package com.jihoon.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.dto.request.auth.SignInDto;
import com.jihoon.board.dto.request.auth.SignUpDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.auth.SignInResponseDto;
import com.jihoon.board.dto.response.auth.SignUpResponseDto;
import com.jihoon.board.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(ApiPattern.AUTH)
@Api(description = "인증 모듈")
public class AuthController {

    @Autowired private AuthService authService;

    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";
    
  
    @ApiOperation(value="회원가입", notes="이메일, 비밀번호, 닉네임, 전화번호, 주소를 입력하여 회원을 등록하고 성공 시에는 회원가입 성공여부에 true가 반환됨") // value : 값 note : 설명
    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
}
