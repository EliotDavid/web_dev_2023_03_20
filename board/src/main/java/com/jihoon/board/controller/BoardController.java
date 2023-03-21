package com.jihoon.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.dto.request.board.LikeDto;
import com.jihoon.board.dto.request.board.PatchBoardDto;
import com.jihoon.board.dto.request.board.PostBoardDto;
import com.jihoon.board.dto.request.board.PostCommentDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.DeleteBoardResponseDto;
import com.jihoon.board.dto.response.board.GetBoardResponseDto;
import com.jihoon.board.dto.response.board.GetListResponseDto;
import com.jihoon.board.dto.response.board.GetMyListResponseDto;
import com.jihoon.board.dto.response.board.GetSearchListResponseDto;
import com.jihoon.board.dto.response.board.GetTop15RelatedSearchWordResponseDto;
import com.jihoon.board.dto.response.board.GetTop15SearchWordResponseDto;
import com.jihoon.board.dto.response.board.LikeResponseDto;
import com.jihoon.board.dto.response.board.PatchBoardResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.dto.response.board.PostCommentResponseDto;
import com.jihoon.board.service.BoardService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {
    
    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";
    private final String POST_COMMENT = "/comment";
    private final String LIKE = "/like";
    
    private final String GET_BOARD = "/{boardNumber}";
    private final String GET_LIST = "/list";
    private final String GET_MY_LIST = "/my-list";
    private final String GET_SEARCH_LIST = "/search-list/{searchWord}";
    private final String GET_SEARCH_LIST_PREVIOUS = "/search-list/{searchWord}/{previousSearchWord}";
    private final String GET_TOP15_SEARCH_WORD = "/top15-search-word";
    private final String GET_TOP15_RELATED_SEARCH_WORD = "/top15-related-search-word/{searchWord}";

    private final String PATCH_BOARD = "";
    
    private final String DELETE_BOARD = "/{boardNumber}";

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
        return response;
    }

    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostCommentDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(email, requestBody);
        return response;
    }

    @PostMapping(LIKE)
    public ResponseDto<LikeResponseDto> like(
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody LikeDto requestBody
    ) {
        ResponseDto<LikeResponseDto> response = boardService.like(email, requestBody);
        return response;
    }

    @GetMapping(GET_BOARD)
    public ResponseDto<GetBoardResponseDto> getBoard(@ApiParam(value="게시물 번호", example = "1", required = true) @PathVariable("boardNumber") int boardNumber) {
        ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }

    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyListResponseDto>> getMyList(@AuthenticationPrincipal String email) {
        ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }

    @GetMapping(value={GET_SEARCH_LIST_PREVIOUS, GET_SEARCH_LIST})
    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(
        @PathVariable("searchWord") String searchWord,
        @PathVariable(name="previousSearchWord", required=false) String previousSearchWord
    ) {
        ResponseDto<List<GetSearchListResponseDto>> response = boardService.getSearchList(searchWord, previousSearchWord);
        return response;
    }

    @GetMapping(GET_TOP15_SEARCH_WORD)
    public ResponseDto<GetTop15SearchWordResponseDto> getTop15SearchWord() {
        ResponseDto<GetTop15SearchWordResponseDto> response = boardService.getTop15SearchWord();
        return response;
    }

    @GetMapping(GET_TOP15_RELATED_SEARCH_WORD)
    public ResponseDto<GetTop15RelatedSearchWordResponseDto> getTop15RelatedSearchWord(@PathVariable("searchWord") String searchWord) {
        ResponseDto<GetTop15RelatedSearchWordResponseDto> response = boardService.getTop15RelatedSearchWord(searchWord);
        return response;
    }

    @PatchMapping(PATCH_BOARD)
    public ResponseDto<PatchBoardResponseDto> patchBoard(
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody PatchBoardDto requestBody
    ) {
        ResponseDto<PatchBoardResponseDto> response = 
            boardService.patchBoard(email, requestBody);
        return response;
    }

    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(
        @AuthenticationPrincipal String email,
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<DeleteBoardResponseDto> response
            = boardService.deleteBoard(email, boardNumber);
        return response;
    }
}
