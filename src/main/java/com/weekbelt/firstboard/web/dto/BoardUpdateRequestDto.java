package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateRequestDto {

    private String boardTitle;
    private String boardContent;
    private String boardType;

    public BoardUpdateRequestDto(Board board) {
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardType = board.getBoardType().name();
    }

    @Builder
    public BoardUpdateRequestDto(String boardTitle, String boardContent, String boardType) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
    }
}
