package com.wzh.myshop.web.admin.controller;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.web.admin.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author wzh
 * @date 2019/9/27 - 11:08
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;
    @GetMapping("list")
    public String list(){
        return "content/list";
    }

    @ResponseBody
    @GetMapping("page")
    public PageInfo<Content> page(@RequestParam(required = false,defaultValue = "0") int draw,
                               @RequestParam(required = false,defaultValue = "0") int start,
                               @RequestParam(required = false,defaultValue = "10") int length, Content content){
        return contentService.page(draw,start,length,content);
    }

    @GetMapping("form")
    public String form(Integer id, Model model){
        Content content = id != null?contentService.selectOne(id):new Content();
        model.addAttribute("content",content);
        return "content/form";
    }

    @PostMapping("save")
    public String save(Content content, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = contentService.save(content);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else{
            model.addAttribute("content",content);
            model.addAttribute("baseResult",baseResult);
            return "content/form";
        }
    }
}
