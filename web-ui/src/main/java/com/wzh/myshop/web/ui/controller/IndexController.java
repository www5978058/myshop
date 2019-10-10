package com.wzh.myshop.web.ui.controller;

import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.web.ui.api.ContentsApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author wzh
 * @date 2019/10/9 - 15:53
 */
@Controller
public class IndexController {
    @GetMapping("index")
    public String index(Model model){
        requestContentsPPT(model);
        return "index";
    }

    /**
     * 请求幻灯片
     *
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<Content> contents = ContentsApi.ppt();
        model.addAttribute("ppt", contents);
    }
}
