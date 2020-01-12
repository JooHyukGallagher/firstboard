package com.weekbelt.firstboard.domain.board;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @After
    public void cleanup() {
        boardRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() throws Exception {
        //given
        String boardTitle = "테스트 게시글";
        String boardContent = "테스트 본문";
        Integer viewCount = 0;
        String boardType = "FREE";

        boardRepository.save(Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .viewCount(viewCount)
                .boardType(boardType)
                .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);
        assertThat(board.getBoardTitle()).isEqualTo(boardTitle);
        assertThat(board.getBoardContent()).isEqualTo(boardContent);
        assertThat(board.getBoardType()).isEqualTo(boardType);
        assertThat(board.getViewCount()).isEqualTo(viewCount);
    }
}