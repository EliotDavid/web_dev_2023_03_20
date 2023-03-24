package com.jihoon.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jihoon.board.dto.request.auth.SignInDto;
import com.jihoon.board.dto.request.auth.SignUpDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.auth.SignInResponseDto;
import com.jihoon.board.dto.response.auth.SignUpResponseDto;

public interface AuthService { // 이 녀석을 Interface로 만듬
    
   public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto); 

 public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
}

