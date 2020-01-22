package com.weekbelt.firstboard.web;

import com.weekbelt.firstboard.config.auth.LoginUser;
import com.weekbelt.firstboard.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "joinForm";
    }
}
