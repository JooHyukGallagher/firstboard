package com.weekbelt.firstboard.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class BoardListResponse {

    private List<BoardListResponseDto> boards;
    private Integer totalPage;

    @Builder
    public BoardListResponse(List<BoardListResponseDto> boards, Integer totalPage) {
        this.boards = boards;
        this.totalPage = totalPage;
    }
}
