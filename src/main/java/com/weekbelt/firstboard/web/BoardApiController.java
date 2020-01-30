package com.weekbelt.firstboard.web;

import com.weekbelt.firstboard.config.auth.LoginUser;
import com.weekbelt.firstboard.config.auth.dto.SessionUser;
import com.weekbelt.firstboard.service.BoardService;
import com.weekbelt.firstboard.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    // Create
    @PostMapping("/board")
    public BoardResponseDto save(@RequestBody BoardSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return boardService.save(requestDto, user);
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
                                                             @RequestParam(defaultValue = "ALL") String boardType) {
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
