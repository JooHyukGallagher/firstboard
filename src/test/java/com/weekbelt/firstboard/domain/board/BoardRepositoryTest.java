package com.weekbelt.firstboard.domain.board;

import com.weekbelt.firstboard.domain.user.Role;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        User user = User.builder().name("joohyuk").email("vfrvfr4207@gmail.com").role(Role.USER).build();
        Long userId = userRepository.save(user).getId();
        User findUser = userRepository.findById(userId).get();

        Board board1 = Board.builder().boardTitle("공지1").boardContent("공지1 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board1.setUser(findUser);
        Board board2 = Board.builder().boardTitle("공지2").boardContent("공지2 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board2.setUser(findUser);
        Board board3 = Board.builder().boardTitle("공지3").boardContent("공지3 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board3.setUser(findUser);
        Board board4 = Board.builder().boardTitle("공지4").boardContent("공지4 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board4.setUser(findUser);
        Board board5 = Board.builder().boardTitle("공지5").boardContent("공지5 입니다.").viewCount(0).boardType("ANNOUNCE").build();
        board5.setUser(findUser);

        Board board6 = Board.builder().boardTitle("자유1").boardContent("자유1 입니다.").viewCount(0).boardType("FREE").build();
        board6.setUser(findUser);
        Board board7 = Board.builder().boardTitle("자유2").boardContent("자유2 입니다.").viewCount(0).boardType("FREE").build();
        board7.setUser(findUser);

        Board board8 = Board.builder().boardTitle("질문1").boardContent("질문1 입니다.").viewCount(0).boardType("QUESTION").build();
        board8.setUser(findUser);

        Board board9 = Board.builder().boardTitle("홍보1").boardContent("홍보1 입니다.").viewCount(0).boardType("PROMOTION").build();
        board9.setUser(findUser);

        boardRepository.save(board1);boardRepository.save(board2);
        boardRepository.save(board3);boardRepository.save(board4);
        boardRepository.save(board5);boardRepository.save(board6);
        boardRepository.save(board7);boardRepository.save(board8);
        boardRepository.save(board9);
    }

    @AfterEach
    public void removeContext() {
        em.clear();
    }

    @DisplayName("Board리스트 조회")
    @Test
    public void findAllDesc() throws Exception {
        //given
        List<Board> allDesc = boardRepository.findAllByOrderByIdDesc();
        //then
        // 게시글 수 확인
        assertThat(allDesc.size()).isEqualTo(9);
        // 게시글 순서 확인
        assertThat(allDesc.get(0).getBoardTitle()).isEqualTo("홍보1");
        assertThat(allDesc.get(8).getBoardTitle()).isEqualTo("공지1");
    }

    @DisplayName("BoardType에 따른 Board리스트 조회")
    @Test
    public void findAllDescByBoardType() throws Exception {
        //given
        List<Board> announceBoardList = boardRepository.findAllByBoardTypeOrderByIdDesc(BoardType.ANNOUNCE);
        List<Board> freeBoardList = boardRepository.findAllByBoardTypeOrderByIdDesc(BoardType.FREE);
        List<Board> questionBoardList = boardRepository.findAllByBoardTypeOrderByIdDesc(BoardType.QUESTION);
        List<Board> promotionBoardList = boardRepository.findAllByBoardTypeOrderByIdDesc(BoardType.PROMOTION);
        //when

        //then
        assertThat(announceBoardList.size()).isEqualTo(5);
        assertThat(freeBoardList.size()).isEqualTo(2);
        assertThat(questionBoardList.size()).isEqualTo(1);
        assertThat(promotionBoardList.size()).isEqualTo(1);

        // 순서
        assertThat(announceBoardList.get(0).getBoardTitle()).isEqualTo("공지5");
        assertThat(announceBoardList.get(0).getBoardContent()).isEqualTo("공지5 입니다.");
        assertThat(announceBoardList.get(0).getBoardType()).isEqualTo(BoardType.ANNOUNCE);
    }

    @DisplayName("게시글 페이징 테스트")
    @Test
    public void pagingTest() throws Exception {
        //given
        //when
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Board> allPage = boardRepository.findAll(pageRequest);
        Page<Board> announcePage = boardRepository.findAllByBoardType(BoardType.ANNOUNCE, pageRequest);

        //then
        List<Board> allContent = allPage.getContent();          // 조회된 데이터
        assertThat(allContent.size()).isEqualTo(3);             // 조회된 데이터 수
        assertThat(allPage.getTotalElements()).isEqualTo(9);    // 전체 데이터 수
        assertThat(allPage.getNumber()).isEqualTo(0);           // 페이지 번호
        assertThat(allPage.getTotalPages()).isEqualTo(3);       // 전체 페이지 번호
        assertThat(allPage.isFirst()).isTrue();                 // 첫번째 항목인가?
        assertThat(allPage.hasNext()).isTrue();                 // 다음 페이지가 있는가?

        List<Board> announceContent = announcePage.getContent();          // 조회된 데이터
        assertThat(announceContent.size()).isEqualTo(3);             // 조회된 데이터 수
        assertThat(announcePage.getTotalElements()).isEqualTo(5);    // 전체 데이터 수
        assertThat(announcePage.getNumber()).isEqualTo(0);           // 페이지 번호
        assertThat(announcePage.getTotalPages()).isEqualTo(2);       // 전체 페이지 번호
        assertThat(announcePage.isFirst()).isTrue();                 // 첫번째 항목인가?
        assertThat(announcePage.hasNext()).isTrue();                 // 다음 페이지가 있는가?
    }
}
