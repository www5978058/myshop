package com.wzh.myshop.web.admin.service;

import com.wzh.myshop.domain.entity.ContentCategory;
import com.wzh.myshop.domain.entity.ContentCategoryExample;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/26 - 10:00
 */
public interface ContentCategoryService {
    /**
     * 查询内容
     * @return
     */
    List<ContentCategory> selectAll();

    /**
     * 根据条件查询
     * @param example
     * @return
     */
    List<ContentCategory> selectByExample(ContentCategoryExample example);
}
