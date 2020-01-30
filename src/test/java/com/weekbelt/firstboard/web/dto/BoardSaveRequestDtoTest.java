package com.weekbelt.firstboard.web.dto;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardSaveRequestDtoTest {

    @Test
    public void toBoardEntity() throws Exception {
        //given
        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
                .boardTitle("자유")
                .boardContent("자유입니다.")
                .boardType(BoardType.FREE.name())
                .viewCount(0)
                .build();

        //when
        Board board = boardSaveRequestDto.toBoardEntity();

        //then
        assertThat(board).isNotNull();
        assertThat(boardSaveRequestDto.getBoardTitle()).isEqualTo(board.getBoardTitle());
        assertThat(boardSaveRequestDto.getBoardContent()).isEqualTo(board.getBoardContent());
        assertThat(boardSaveRequestDto.getBoardType()).isEqualTo(board.getBoardType().name());
        assertThat(boardSaveRequestDto.getViewCount()).isEqualTo(board.getViewCount());
    }

}