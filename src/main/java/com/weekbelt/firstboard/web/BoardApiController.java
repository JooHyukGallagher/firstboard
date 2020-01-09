package com.weekbelt.firstboard.web;

import com.weekbelt.firstboard.service.BoardService;
import com.weekbelt.firstboard.web.dto.BoardResponseDto;
import com.weekbelt.firstboard.web.dto.BoardSaveRequestDto;
import com.weekbelt.firstboard.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public BoardResponseDto findById (@PathVariable Long id) {
        return boardService.findById(id);
    }
}