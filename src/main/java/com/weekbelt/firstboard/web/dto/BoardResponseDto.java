package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Integer viewCount;

    public BoardResponseDto (Board board) {
        this.id = board.getId();
        this.title = board.getBoardTitle();
        this.content = board.getBoardContent();
        this.viewCount = board.getViewCount();
    }
}
