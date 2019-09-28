package com.wzh.myshop.web.admin.service;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.User;

/**
 * @author wzh
 * @date 2019/9/27 - 11:03
 */
public interface ContentService {

    /**
     * 分页查询
     * @param draw  页码
     * @param start 开始位置
     * @param length 查询数量
     * @param content 条件查询
     * @return
     */
    PageInfo<Content> page(int draw, int start, int length, Content content);
    /**
     * 根据主键查询内容
     * @param id
     * @return
     */
    Content selectOne(Integer id);

    /**
     * 保存内容信息
     * @param content
     */
    BaseResult save(Content content);
}
