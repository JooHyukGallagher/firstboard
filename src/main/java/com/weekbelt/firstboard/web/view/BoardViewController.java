package com.weekbelt.firstboard.web.view;

import com.weekbelt.firstboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardViewController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model){
        model.addAttribute("board", boardService.findAllDesc());
        return "/board/boardList";
    }

    @GetMapping("/save")
    public String boardWriteForm(){
        return "/board/boardWriteForm";
    }

    @GetMapping("/update/{boardId}")
    public String boardUpdateForm(@PathVariable Long boardId, Model model) {
        model.addAttribute("board", boardService.findById(boardId));
        return "/board/boardUpdateForm";
    }

    @GetMapping("/read/{boardId}")
    public String boardReadForm(@PathVariable Long boardId, Model model) {
        model.addAttribute("board", boardService.findById(boardId));
        return "/board/boardReadForm";
    }

}
