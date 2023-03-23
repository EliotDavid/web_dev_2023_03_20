package com.jihoon.board.dto.response.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "전체 게시물 리스트 가져오기 Request Body")
@Data
@NoArgsConstructor
public class GetSearchListDto {
    @ApiModelProperty(value="", example="", required=true)
    private String searchWord;
    @ApiModelProperty(value="", example="", required=true)
    private String previousSearchWord;
}
