package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.config.auth.dto.SessionUser;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static java.util.Optional.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Mock
    BoardRepository boardRepository;
    @Mock
    UserRepository userRepository;

    @Test
    public void save() throws Exception {
        //given
        SessionUser sessionUser = SessionUser.builder()
                .email("vfrvfr4207@gmail.com")
                .name("김주혁")
                .build();

        BoardSaveRequestDto boardSaveRequestDto = BoardSaveRequestDto.builder()
                .boardTitle("자유1")
                .boardContent("자유입니다.")
                .boardType(BoardType.FREE.name())
                .viewCount(0)
                .build();

        User user = User.builder()
                .email("vfrvfr4207@gmail.com")
                .name("김주혁")
                .build();

        Board board = Board.builder()
                .boardTitle("자유1")
                .boardContent("자유입니다.")
                .boardType(BoardType.FREE.name())
                .viewCount(0)
                .build();

        board.setUser(user);
        //when
        when(userRepository.findByEmail(any(String.class))).thenReturn(of(user));
        when(boardRepository.save(board)).thenReturn(board);

        //then
        boardService.save(boardSaveRequestDto, sessionUser);

        verify(userRepository).findByEmail(user.getEmail());
        verify(boardRepository).save(any(Board.class));
    }
}