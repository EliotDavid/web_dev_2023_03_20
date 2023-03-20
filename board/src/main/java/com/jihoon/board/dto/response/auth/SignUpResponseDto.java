package com.jihoon.board.dto.response.auth;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto {
    @ApiParam(name="로그인 결과")
    private boolean status;
}
