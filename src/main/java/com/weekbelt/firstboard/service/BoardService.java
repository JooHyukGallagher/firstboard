package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.config.auth.dto.SessionUser;
import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.domain.board.BoardType;
import com.weekbelt.firstboard.domain.user.User;
import com.weekbelt.firstboard.domain.user.UserRepository;
import com.weekbelt.firstboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final static int SIZE = 10;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto, SessionUser user) {
        User findUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다. email=" + user.getEmail()));

        Board board = requestDto.toEntity();
        board.setUser(findUser);

        return boardRepository.save(board).getId();
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

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. boardId=" + boardId));

        boardRepository.delete(board);
    }

}
