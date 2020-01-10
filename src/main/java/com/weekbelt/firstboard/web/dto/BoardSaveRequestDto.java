package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String boardTitle;
    private String boardContent;
    private String boardType;
    private Integer viewCount;

    @Builder
    public BoardSaveRequestDto(String boardTitle, String boardContent, String boardType, Integer viewCount) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardType = boardType;
        this.viewCount = viewCount;
    }

    public Board toEntity() {
        return Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardType(boardType)
                .viewCount(viewCount)
                .build();
    }
}
