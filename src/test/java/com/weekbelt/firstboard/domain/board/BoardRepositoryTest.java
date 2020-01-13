package com.weekbelt.firstboard.domain.board;

import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void Init() throws Exception {
        System.out.println("Init");
        User user = User.builder().userName("vfrvfr4207").userPw("123").nickname("weekbelt").email("vfrvfr4207@gmail.com").message("반갑습니다.").build();
        Long userId = userRepository.save(user).getId();
        User findUser = userRepository.findById(userId).get();

        Board board1 = Board.builder().boardTitle("공지1").boardContent("공지1 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board1.setUser(findUser);
        Board board2 = Board.builder().boardTitle("공지2").boardContent("공지2 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board2.setUser(findUser);
        Board board3 = Board.builder().boardTitle("공지3").boardContent("공지3 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board3.setUser(findUser);
        Board board4 = Board.builder().boardTitle("자유1").boardContent("자유1 입니다.").viewCount(0).boardType("FREE").build();
        board4.setUser(findUser);
        Board board5 = Board.builder().boardTitle("자유2").boardContent("자유2 입니다.").viewCount(0).boardType("FREE").build();
        board5.setUser(findUser);
        Board board6 = Board.builder().boardTitle("질문1").boardContent("질문1 입니다.").viewCount(0).boardType("QUESTION").build();
        board6.setUser(findUser);
        Board board7 = Board.builder().boardTitle("홍보").boardContent("홍보1 입니다.").viewCount(0).boardType("PROMOTION").build();
        board7.setUser(findUser);

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        boardRepository.save(board5);
        boardRepository.save(board6);
        boardRepository.save(board7);
    }

    @AfterEach
    public void removeContext() {
        em.clear();
    }

    @DisplayName("Board리스트 조회")
    @Test
    public void findAllDesc() throws Exception {
        //given
        List<Board> allDesc = boardRepository.findAllDesc();
        //then
        assertThat(allDesc.size()).isEqualTo(7);
    }

    @DisplayName("BoardType에 따른 Board리스트 조회")
    @Test
    public void findAllDescByBoardType() throws Exception {
        //given
        List<Board> announceBoardList = boardRepository.findAllDescByBoardType(BoardType.ANNOUNCE);
        List<Board> freeBoardList = boardRepository.findAllDescByBoardType(BoardType.FREE);
        List<Board> questionBoardList = boardRepository.findAllDescByBoardType(BoardType.QUESTION);
        List<Board> promotionBoardList = boardRepository.findAllDescByBoardType(BoardType.PROMOTION);
        //when

        //then
        assertThat(announceBoardList.size()).isEqualTo(3);
        assertThat(freeBoardList.size()).isEqualTo(2);
        assertThat(questionBoardList.size()).isEqualTo(1);
        assertThat(promotionBoardList.size()).isEqualTo(1);
    }
}
