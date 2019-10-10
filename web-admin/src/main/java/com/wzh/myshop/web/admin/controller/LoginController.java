package com.wzh.myshop.web.admin.controller;

import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.commons.constant.WebConstant;
import com.wzh.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wzh
 * @date 2019/9/22 - 15:30
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping(value = {"","login"})
    public String index(HttpSession session) {
        if (session.getAttribute(WebConstant.SESSION_USER) != null) {
            return "redirect:main";
        }
        return "login";
    }

    @PostMapping("login")
    public String login(String email, String password, String remember, Model model, HttpSession session) {
        User user = userService.Login(email, password);
        if (user == null) {
            model.addAttribute("message", "用户名或密码错误");
            return "login";
        }
        session.setAttribute(WebConstant.SESSION_USER, user);
        return "redirect:main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
