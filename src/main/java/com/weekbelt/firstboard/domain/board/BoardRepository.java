package com.weekbelt.firstboard.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b order by b.id desc")
    List<Board> findAllDesc();

    @Query("select distinct b from Board b" +
            " join fetch b.user u" +
            " where b.boardType = :boardType" +
            " order by b.id desc")
    List<Board> findAllDescByBoardType(@Param("boardType") BoardType boardType);


}
