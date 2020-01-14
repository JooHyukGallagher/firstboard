package com.weekbelt.firstboard.domain.board;

import com.weekbelt.firstboard.domain.reply.Reply;
import com.weekbelt.firstboard.domain.reply.ReplyRepository;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReplyRepository replyRepository;

    @AfterEach
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
        assertThat(board.getBoardType()).isEqualTo(BoardType.FREE);
        assertThat(board.getViewCount()).isEqualTo(viewCount);
    }

    @Transactional
    @Test
    public void boardListByBoardType() throws Exception {
        //given
        // User
        User user1 = User.builder()
                .userName("vfrvfr4207").userPw("123").nickname("weekbelt").message("반갑습니다.").email("vfrvfr4207@hamail.net")
                .build();

        User user2 = User.builder()
                .userName("bgtbgt1234").userPw("123").nickname("twins").message("안녕하세요").email("twins@gmail.com")
                .build();

        // User 저장
        Long user1Id = userRepository.save(user1).getId();
        Long user2Id = userRepository.save(user2).getId();

        // Board
        Board board1 = Board.builder()
                .boardTitle("게시글1").boardContent("공지글 입니다.").viewCount(0).boardType("ANNOUNCE")
                .build();

        Board board2 = Board.builder()
                .boardTitle("게시글2").boardContent("자유글 입니다.").viewCount(0).boardType("FREE")
                .build();

        Board board3 = Board.builder()
                .boardTitle("게시글3").boardContent("질문글 입니다.").viewCount(0).boardType("QUESTION")
                .build();

        Board board4 = Board.builder()
                .boardTitle("게시글4").boardContent("홍보글 입니다.").viewCount(0).boardType("PROMOTION")
                .build();

        User findUser1 = userRepository.findById(user1Id).get();
        User findUser2 = userRepository.findById(user2Id).get();

        // User, Board 연관관계 매핑
        board1.setUser(findUser1);
        board2.setUser(findUser1);
        board3.setUser(findUser2);
        board4.setUser(findUser2);

        // Board 저장
        Long board1Id = boardRepository.save(board1).getId();
        Long board2Id = boardRepository.save(board2).getId();
        Long board3Id = boardRepository.save(board3).getId();
        Long board4Id = boardRepository.save(board4).getId();

        // Reply
        Reply reply1 = Reply.builder().replyContent("공지 1 댓글 입니다.").build();
        Reply reply2 = Reply.builder().replyContent("공지 2 댓글 입니다.").build();

        Reply reply3 = Reply.builder().replyContent("자유 1 댓글 입니다.").build();

        Reply reply4 = Reply.builder().replyContent("질문 1 댓글 입니다.").build();


        Board findBoard1 = boardRepository.findById(board1Id).get();
        Board findBoard2 = boardRepository.findById(board2Id).get();
        Board findBoard3 = boardRepository.findById(board3Id).get();
        Board findBoard4 = boardRepository.findById(board4Id).get();


        reply1.setUser(findUser1);
        reply1.setBoard(findBoard1);

        reply2.setUser(findUser2);
        reply2.setBoard(findBoard1);

        reply3.setUser(findUser2);
        reply3.setBoard(findBoard2);

        reply4.setUser(findUser1);
        reply4.setBoard(findBoard3);

        replyRepository.save(reply1);
        replyRepository.save(reply2);
        replyRepository.save(reply3);
        replyRepository.save(reply4);

        //when
        List<Board> announceBoardList = boardRepository.findAllDescByBoardType(BoardType.ANNOUNCE);
        List<Board> freeBoardList = boardRepository.findAllDescByBoardType(BoardType.FREE);
        List<Board> questionBoardList = boardRepository.findAllDescByBoardType(BoardType.QUESTION);
        List<Board> promotionBoardList = boardRepository.findAllDescByBoardType(BoardType.PROMOTION);

        //then

        // 카테고리별 게시글 갯수 확인
        assertThat(announceBoardList.size()).isEqualTo(1);
        assertThat(freeBoardList.size()).isEqualTo(1);
        assertThat(questionBoardList.size()).isEqualTo(1);
        assertThat(promotionBoardList.size()).isEqualTo(1);

        // 타입 확인
        assertThat(announceBoardList.get(0).getBoardType()).isEqualTo(BoardType.ANNOUNCE);
        assertThat(freeBoardList.get(0).getBoardType()).isEqualTo(BoardType.FREE);
        assertThat(questionBoardList.get(0).getBoardType()).isEqualTo(BoardType.QUESTION);
        assertThat(promotionBoardList.get(0).getBoardType()).isEqualTo(BoardType.PROMOTION);
    }
}