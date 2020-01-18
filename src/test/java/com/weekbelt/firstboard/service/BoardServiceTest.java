package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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

        //when

        //then
    }
}