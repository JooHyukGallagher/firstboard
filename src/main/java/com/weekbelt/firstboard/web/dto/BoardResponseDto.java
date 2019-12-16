package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String boardTitle;
    private String boardContent;
    private Integer viewCount;

    public BoardResponseDto (Board board) {
        this.id = board.getId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
    }
}
