package com.jihoon.board.dto.response.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="회원가입 결과")
public class SignUpResponseDto {
    @ApiModelProperty(value="회원가입 결과", example = "true", required = true ) // 회원가입결과는 무조건 true라고 true를 적는다고 하는데 왜 무조건 true라고 생각하지?
    private boolean status;
}
