package com.weekbelt.firstboard.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByIdDesc();

    List<Board> findAllByBoardTypeOrderByIdDesc(BoardType boardType);

    Page<Board> findAll(Pageable pageable);
    Page<Board> findAllByBoardType(BoardType boardType, Pageable pageable);

//    TODO : User 로직 추가했을때(최적화 시)
//    @Query("select distinct b from Board b" +
//            " join fetch b.user u" +
//            " where b.boardType = :boardType" +
//            " order by b.id desc")
//    List<Board> findAllDescByBoardType(@Param("boardType") BoardType boardType);
}
