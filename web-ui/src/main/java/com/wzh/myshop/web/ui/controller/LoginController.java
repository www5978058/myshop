package com.wzh.myshop.web.ui.controller;

import com.wzh.myshop.commons.constant.WebUiConstant;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.web.ui.api.UserApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wzh
 * @date 2019/10/9 - 14:13
 */
@Controller
public class LoginController {
    @GetMapping({"","/login"})
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String email, String password, Model model, HttpServletRequest request) throws Exception {
        User user = UserApi.login(email, password);
        if(user == null){
            model.addAttribute("message","用户名或密码错误");
            return "login";
        }
        request.getSession().setAttribute(WebUiConstant.WEB_UI_SESSION_USER,user);
        return "redirect:/index";
    }

    /**
     * 注销
     * @return
     */
    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
