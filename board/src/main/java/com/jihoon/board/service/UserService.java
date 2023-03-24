package com.jihoon.board.service;

import org.springframework.stereotype.Service;

import com.jihoon.board.dto.request.user.PatchProfileDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.user.PatchProfileResponseDto;

@Service
public interface UserService {
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto dto);

    
    
}
