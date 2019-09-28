package com.wzh.myshop.web.admin.service.impl;

import com.wzh.myshop.domain.entity.ContentCategory;
import com.wzh.myshop.domain.entity.ContentCategoryExample;
import com.wzh.myshop.web.admin.mapper.ContentCategoryMapper;
import com.wzh.myshop.web.admin.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/26 - 10:00
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<ContentCategory> selectAll(){
        ContentCategoryExample example = new ContentCategoryExample();
        example.setOrderByClause("parent_id asc,sort_order asc,is_parent desc");
        return contentCategoryMapper.selectByExample(example);
    }

    @Override
    public List<ContentCategory> selectByExample(ContentCategoryExample example){
        return contentCategoryMapper.selectByExample(example);
    }
}
