package com.jihoon.board.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@ApiModel(value="회원가입 Reqeust Body")
public class SignUpDto {
    @ApiModelProperty(value="사용자 이메일", example="qwer@qwer.com", required = true)
    @NotBlank
    @Email
    @Length(max=45)
    private String email;

    @ApiModelProperty(value="사용자 비밀번호", example="p!ssw0rd", required = true)
    @NotBlank
    @Length(min=8, max=20)
    private String password;

    @NotBlank
    @Length(min=3, max=20)
    @ApiModelProperty(value="사용자 닉네임", example="Jiraynor", required = true) // required=true : 필수다라는 뜻
    private String nickname;

    @NotBlank
    @Length(min=11, max=13)
    @ApiModelProperty(value="사용자 휴대전화번호", example="010-1234-9876", required = true)
    private String telNumber;
    
    @NotBlank
    @ApiModelProperty(value="사용자 주소", example="부산광역시 부산진구", required = true) // 주소는 왜 필수라고 적지?
    private String address;
}
