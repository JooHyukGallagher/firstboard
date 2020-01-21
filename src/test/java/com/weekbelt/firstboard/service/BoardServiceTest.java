package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.domain.user.Role;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Autowired
    BoardService boardService;
    @Mock
    BoardRepository boardRepository;
    @Mock
    UserRepository userRepository;

    @DisplayName("게시글 조회시 조회수가 1씩 증가한다.")
    @Test
    public void findById() throws Exception {
        //given
        User user = User.builder()
                .role(Role.USER)
                .name("weekbelt")
                .email("vfrvfr4207@gmail.com")
                .build();

        Board board = Board.builder()
                .boardTitle("자유")
                .boardContent("자유 입니다.")
                .boardType(BoardType.FREE.name())
                .build();

        board.setUser(user);
        //when
//        when(boardService.findById())
        //then
    }
}