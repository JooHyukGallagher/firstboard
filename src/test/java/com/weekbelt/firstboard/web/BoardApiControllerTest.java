package com.weekbelt.firstboard.web;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import com.weekbelt.firstboard.service.BoardService;
import com.weekbelt.firstboard.web.dto.BoardListResponseDto;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import com.weekbelt.firstboard.web.dto.BoardUpdateRequestDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    UserRepository userRepository;

    @AfterAll
    public void tearDown() throws Exception {
        boardRepository.deleteAll();
    }

    @Test
    public void Board_등록된다() throws Exception {
        //given
        String boardTitle = "title";
        String boardContent = "content";
        String boardType = "FREE";
        Integer viewCount = 0;

        BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardType(boardType)
                .viewCount(viewCount)
                .build();

        String url = "http://localhost:" + port + "/api/board";
        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getBoardTitle()).isEqualTo(boardTitle);
        assertThat(all.get(0).getBoardContent()).isEqualTo(boardContent);
        assertThat(all.get(0).getBoardType()).isEqualTo(BoardType.FREE);
        assertThat(all.get(0).getViewCount()).isEqualTo(viewCount);

    }

    @Test
    public void Board_수정된다() throws Exception {
        //given
        Board savedBoard = boardRepository.save(Board.builder()
                    .boardTitle("제목입니다")
                    .boardContent("내용입니다.")
                    .boardType("FREE")
                    .viewCount(0)
                    .build());

        Long updateId = savedBoard.getId();
        String expectedTitle = "제목";
        String expectedContent = "내용";
        String expectedBoardType= "PROMOTION";

        // 업데이트 요청 객체
        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .boardTitle(expectedTitle)
                .boardContent(expectedContent)
                .boardType(expectedBoardType)
                .build();

        String url = "http://localhost:" + port + "/api/board/" + updateId;

        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getBoardTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getBoardContent()).isEqualTo(expectedContent);
        assertThat(all.get(0).getBoardType()).isEqualTo(BoardType.PROMOTION);
    }

    @Test
    public void BaseTimeEntity_등록() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

        boardRepository.save(Board.builder()
                .boardTitle("title")
                .boardContent("content")
                .viewCount(0)
                .boardType("FREE")
                .build());
        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        System.out.printf(">>>>>>>> createDate=" + board.getCreateDate() + ", modifiedDate=" + board.getModifyDate());

        assertThat(board.getCreateDate()).isAfter(now);
        assertThat(board.getModifyDate()).isAfter(now);
    }

    @Transactional
    @Test
    public void 카테고리별게시글() throws Exception {
        //given
        User user = User.builder()
                .userName("vfrvfr4207").userPw("123").message("안녕하세요").email("vfrvfr4207@hanmail.net").nickname("weekbelt")
                .build();

        Long userId = userRepository.save(user).getId();

        Board board1 = Board.builder()
                .boardTitle("자유1").boardContent("자유 입니다.").boardType("FREE").viewCount(0)
                .build();
        Board board2 = Board.builder()
                .boardTitle("공지1").boardContent("공지 입니다.").boardType("ANNOUNCE").viewCount(0)
                .build();
        Board board3 = Board.builder()
                .boardTitle("공지1").boardContent("질문 입니다.").boardType("QUESTION").viewCount(0)
                .build();
        Board board4 = Board.builder()
                .boardTitle("홍보1").boardContent("홍보 입니다.").boardType("PROMOTION").viewCount(0)
                .build();

        User findUser = userRepository.findById(userId).get();

        board1.setUser(findUser);
        board2.setUser(findUser);
        board3.setUser(findUser);
        board4.setUser(findUser);

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
//
//        String announceUrl = "http://localhost:" + port + "/api/board/list?boardType=ANNOUNCE";
//        String freeUrl = "http://localhost:" + port + "/api/board/list?boardType=FREE";
//        String questionUrl = "http://localhost:" + port + "/api/board/list?boardType=QUESTION";
//        String promotionUrl = "http://localhost:" + port + "/api/board/list?boardType=PROMOTION";

        //when
        List<BoardListResponseDto> freeBoardList = boardService.findAllDescByBoardType("FREE");
        List<BoardListResponseDto> announceBoardList = boardService.findAllDescByBoardType("ANNOUNCE");
        List<BoardListResponseDto> questionBoardList = boardService.findAllDescByBoardType("QUESTION");
        List<BoardListResponseDto> promotionBoardList = boardService.findAllDescByBoardType("PROMOTION");

        //then
        assertThat(freeBoardList.size()).isEqualTo(1);
        assertThat(announceBoardList.size()).isEqualTo(1);
        assertThat(questionBoardList.size()).isEqualTo(1);
        assertThat(promotionBoardList.size()).isEqualTo(1);

        assertThat(freeBoardList.get(0).getBoardType()).isEqualTo("자유");
        assertThat(announceBoardList.get(0).getBoardType()).isEqualTo("공지");
        assertThat(questionBoardList.get(0).getBoardType()).isEqualTo("질문");
        assertThat(promotionBoardList.get(0).getBoardType()).isEqualTo("홍보");
    }
}