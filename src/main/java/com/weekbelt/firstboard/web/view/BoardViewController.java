package com.weekbelt.firstboard.web.view;

import com.weekbelt.firstboard.config.auth.LoginUser;
import com.weekbelt.firstboard.config.auth.dto.SessionUser;
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

    private final HttpSession httpSession;
    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "/board/boardList";
    }

    @GetMapping("/save")
    public String boardWriteForm(Model model, @LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "/board/boardWriteForm";
    }

    @GetMapping("/update")
    public String boardUpdateForm(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute("board", httpSession.getAttribute("board"));

        httpSession.removeAttribute("board");

        return "/board/boardUpdateForm";
    }

    @GetMapping("/read/{boardId}")
    public String boardReadForm(@PathVariable Long boardId, Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        httpSession.setAttribute("board", boardResponseDto);
//        model.addAttribute("board", boardResponseDto);
        return "/board/boardReadForm";
    }

}
