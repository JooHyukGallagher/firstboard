package com.weekbelt.firstboard.service;

import com.weekbelt.firstboard.domain.board.Board;
import com.weekbelt.firstboard.domain.board.BoardRepository;
import com.weekbelt.firstboard.web.dto.BoardResponseDto;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import com.weekbelt.firstboard.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto reqeustDto) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        findBoard.update(reqeustDto.getBoardTitle(), reqeustDto.getBoardContent());

        return id;
    }

    public BoardResponseDto findById(Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new BoardResponseDto(findBoard);
    }
}
