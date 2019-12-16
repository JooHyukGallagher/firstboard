package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateRequestDto {

    private String boardTitle;
    private String boardContent;

    public BoardUpdateRequestDto(Board board) {
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
    }

    @Builder
    public BoardUpdateRequestDto(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
