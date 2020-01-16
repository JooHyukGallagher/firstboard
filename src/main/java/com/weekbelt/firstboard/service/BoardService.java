package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BoardService {
    private final static int SIZE = 10;

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long boardId, BoardUpdateRequestDto requestDto) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. boarId=" + boardId));

        findBoard.update(requestDto);
        return boardId;
    }

    @Transactional
    public BoardResponseDto findById(Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        findBoard.plusViewCount();
        return new BoardResponseDto(findBoard);
    }

    @Transactional(readOnly = true)
    public Page<BoardListResponseDto> findAllDesc(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE, Sort.Direction.DESC, "id");
        Page<Board> boardPage = boardRepository.findAll(pageRequest);

        return boardPage.map(BoardListResponseDto::new);
    }

    @Transactional(readOnly = true)
    public Page<BoardListResponseDto> findAllDescByBoardType(Integer page, String boardType) {
        PageRequest pageRequest = PageRequest.of(page, SIZE, Sort.Direction.DESC, "id");
        Page<Board> boardPage = boardRepository.findAllByBoardType(BoardType.valueOf(boardType), pageRequest);

        return boardPage.map(BoardListResponseDto::new);
    }

//    @Transactional(readOnly = true)
//    public List<BoardListResponseDto> findAllDescByBoardType(String boardType) {
//        return boardRepository.findAllByBoardTypeOrderByIdDesc(BoardType.valueOf(boardType))
//                .stream().map(BoardListResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardId=" + boardId));

        boardRepository.delete(board);
    }

}
