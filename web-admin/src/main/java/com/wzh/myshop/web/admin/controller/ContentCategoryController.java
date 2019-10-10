package com.wzh.myshop.web.admin.controller;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.domain.entity.ContentCategory;
import com.wzh.myshop.domain.entity.ContentCategoryExample;
import com.wzh.myshop.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2019/9/26 - 10:01
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController {
    @Autowired
    ContentCategoryService contentCategoryService;

    @ModelAttribute
    public ContentCategory getContentCategory(Integer id){
        ContentCategory contentCategory = new ContentCategory();
        ContentCategory parent = new ContentCategory();
        if(id != null){
            contentCategory = contentCategoryService.selectOne(id);
            if (contentCategory.getParentId() != 0) {
                parent = contentCategoryService.selectOne(contentCategory.getParentId());
            }
        }
        contentCategory.setParent(parent);
        return contentCategory;
    }
    @RequestMapping("list")
    public String list(Model model){
        List<ContentCategory> targetList = new ArrayList<>();
        ContentCategoryExample example = new ContentCategoryExample();
        example.setOrderByClause("parent_id asc,sort_order asc,is_parent desc");
        sortList(contentCategoryService.selectByExample(example),targetList,0);
        model.addAttribute("list",targetList);
        return "contentCategory/list";
    }

    @PostMapping("save")
    public String save(ContentCategory contentCategory, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = contentCategoryService.save(contentCategory,"保存内容分类信息成功");
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/contentCategory/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "contentCategory/form";
        }
    }

    @GetMapping("form")
    public String form(ContentCategory contentCategory){
        if(contentCategory.getParentId() != null){
            contentCategory.setParent(contentCategoryService.selectOne(contentCategory.getParentId()));
        }
        return "contentCategory/form";
    }

    @PostMapping("/del")
    @ResponseBody
    public BaseResult del(Integer ids){
        contentCategoryService.delete(ids);
        return BaseResult.ok("删除分类成功");
    }
    @ResponseBody
    @PostMapping("/treeData")
    public List<ContentCategory> treeData(@RequestParam(required = false,defaultValue = "0") Integer id){
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(id);
        return contentCategoryService.selectByExample(example);
    }

    private static void sortList(List<ContentCategory> sourceList,List<ContentCategory> targetList,Integer parentId){
        for (ContentCategory sourceContentCategory : sourceList) {
            if (sourceContentCategory.getParentId().equals(parentId)) {
                targetList.add(sourceContentCategory);
                //判断是否有子节点
                if (sourceContentCategory.getIsParent().equals(1)) {
                    sortList(sourceList,targetList,sourceContentCategory.getId());
                }
            }
        }
    }


}
