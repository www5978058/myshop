package com.wzh.myshop.web.admin.controller;

import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.web.admin.service.UserService;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author wzh
 * @date 2019/9/23 - 9:23
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public String list(Model model){
        return "user/list";
    }
    @GetMapping("form")
    public String form(Integer id,Model model){
        User user = id != null?userService.selectOne(id):new User();
        model.addAttribute("user",user);
        return "user/form";
    }

    @PostMapping("save")
    public String save(User user, Model model,RedirectAttributes redirectAttributes){
        BaseResult baseResult = userService.save(user);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else{
            model.addAttribute("user",user);
            model.addAttribute("baseResult",baseResult);
            return "user/form";
        }
    }

    @RequestMapping("del")
    @ResponseBody
    public BaseResult del(Integer[] ids){
        userService.deleteMul(ids);
        return BaseResult.ok();
    }

    @ResponseBody
    @GetMapping("page")
    public PageInfo<User> page(@RequestParam(required = false,defaultValue = "0") int draw,
                         @RequestParam(required = false,defaultValue = "0") int start,
                         @RequestParam(required = false,defaultValue = "10") int length,User user){
        return userService.page(draw,start,length,user);
    }
    @GetMapping("detail")
    public String detail(int id, Model model){
        User user = userService.selectOne(id);
        model.addAttribute("user",user);
        return "/user/detail";
    }
}
