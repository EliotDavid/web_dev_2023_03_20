package com.jihoon.board.dto.request.board;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("특정 게시물 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchBoardDto {
    @ApiModelProperty(value="게시물 번호", example="1", required=true)
    @Min(1)
    private int boardNumber;
    
    @ApiModelProperty(value="게시물 제목", example="modified Title", required=true)
    @NotBlank
    private String boardTitle;
    
    @ApiModelProperty(value="게시물 내용", example="Modified Content", required=true)
    @NotBlank
    private String boardContent;
    
    @ApiModelProperty(value="게시물 이미지 URL", example="http://~", required=false) // 얘는 required가 false임
    private String boardImgUrl;
}
