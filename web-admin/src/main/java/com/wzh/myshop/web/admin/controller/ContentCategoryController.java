package com.wzh.myshop.web.admin.controller;

import com.wzh.myshop.domain.entity.ContentCategory;
import com.wzh.myshop.domain.entity.ContentCategoryExample;
import com.wzh.myshop.web.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("list")
    public String list(Model model){
        List<ContentCategory> targetList = new ArrayList<>();
        ContentCategoryExample example = new ContentCategoryExample();
        example.setOrderByClause("parent_id asc,sort_order asc,is_parent desc");
        sortList(contentCategoryService.selectByExample(example),targetList,0);
        model.addAttribute("list",targetList);
        return "contentCategory/list";
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
