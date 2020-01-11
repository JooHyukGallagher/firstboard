package com.weekbelt.firstboard.domain.board;

import lombok.Getter;

@Getter
public enum BoardType {
    announce("공지"),
    free("자유"),
    question("질문"),
    promotion("홍보");

    private String boardType;

    BoardType(String boardType) {
        this.boardType = boardType;
    }
}
