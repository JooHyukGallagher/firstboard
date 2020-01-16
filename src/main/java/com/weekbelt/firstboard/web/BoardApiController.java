package com.weekbelt.firstboard.web;

import com.weekbelt.firstboard.service.BoardService;
import com.weekbelt.firstboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    // Create
    @PostMapping("/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        return boardService.save(requestDto);
    }

    // Update
    @PutMapping("/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    // Read
    @GetMapping("/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @GetMapping("/board/list")
    public Page<BoardListResponseDto> findAllDescByBoardType(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam String boardType) {
        if (boardType.equals("ALL")) {
            return boardService.findAllDesc(page);
        }
        return boardService.findAllDescByBoardType(page, boardType);
    }

    // Delete
    @DeleteMapping("/board/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }
}
