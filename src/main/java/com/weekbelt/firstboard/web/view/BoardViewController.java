package com.weekbelt.firstboard.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardViewController {

    @GetMapping("/list")
    public String boardList(){
        return "/board/boardList";
    }

    @GetMapping("/save")
    public String boardWriteForm(){
        return "/board/boardWriteForm";
    }
}
