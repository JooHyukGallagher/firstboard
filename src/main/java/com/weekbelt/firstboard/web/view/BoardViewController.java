package com.weekbelt.firstboard.web.view;

import com.weekbelt.firstboard.service.BoardService;
import com.weekbelt.firstboard.web.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/update")
    public String boardUpdateForm(Model model, HttpSession httpSession) {
        model.addAttribute("board", httpSession.getAttribute("board"));

        httpSession.invalidate();

        return "/board/boardUpdateForm";
    }

    @GetMapping("/read/{boardId}")
    public String boardReadForm(@PathVariable Long boardId, Model model, HttpSession httpSession) {
        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        httpSession.setAttribute("board", boardResponseDto);
        model.addAttribute("board", boardResponseDto);
        return "/board/boardReadForm";
    }

}
