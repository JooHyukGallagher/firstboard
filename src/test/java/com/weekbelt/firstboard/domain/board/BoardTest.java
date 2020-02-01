package com.weekbelt.firstboard.domain.board;

import com.weekbelt.firstboard.domain.user.Role;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.web.dto.BoardResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("Board -> BoardResponseDto")
    @Test
    public void getBoardResponseDto() {
        //given
        User user = User.builder()
                .email("vfrvfr4207@gmail.com")
                .name("김주혁")
                .role(Role.USER)
                .build();

        Board board = Board.builder()
                .boardTitle("자유")
                .boardContent("자유 입니다.")
                .viewCount(0)
                .boardType(BoardType.FREE.name())
                .build();

        board.setUser(user);

        //when
        BoardResponseDto boardResponseDto = board.getBoardResponseDto();

        //then
        assertThat(boardResponseDto).isNotNull();
        assertThat(board.getBoardTitle()).isEqualTo(boardResponseDto.getTitle());
        assertThat(board.getBoardContent()).isEqualTo(boardResponseDto.getContent());
        assertThat(board.getBoardType().name()).isEqualTo(boardResponseDto.getBoardType());
        assertThat(board.getViewCount()).isEqualTo(boardResponseDto.getViewCount());
    }

    @DisplayName("조회수 증가")
    @Test
    public void plusViewCount() throws Exception {
        //given
        User user = User.builder()
                .email("vfrvfr4207@gmail.com")
                .name("김주혁")
                .role(Role.USER)
                .build();

        Board board = Board.builder()
                .boardTitle("자유")
                .boardContent("자유 입니다.")
                .viewCount(0)
                .boardType(BoardType.FREE.name())
                .build();

        board.setUser(user);

        //when
        board.plusViewCount();

        //then
        assertThat(1).isEqualTo(board.getViewCount());
    }
}