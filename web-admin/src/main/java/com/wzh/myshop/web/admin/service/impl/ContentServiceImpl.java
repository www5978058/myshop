package com.wzh.myshop.web.admin.service.impl;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import com.wzh.myshop.commons.utils.MyEAESUtil;
import com.wzh.myshop.commons.validator.BeanValidator;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.ContentExample;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.domain.entity.UserExample;
import com.wzh.myshop.web.admin.mapper.ContentMapper;
import com.wzh.myshop.web.admin.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/27 - 11:03
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentMapper contentMapper;
    @Override
    public PageInfo<Content> page(int draw, int start, int length, Content content) {
        PageInfo<Content> pageInfo = new PageInfo<>();
        ContentExample example = new ContentExample();
        ContentExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(content.getTitle())) {
            criteria.andTitleLike("%" + content.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(content.getSubTitle())) {
            criteria.andSubTitleLike("%" + content.getSubTitle() + "%");
        }
        if (StringUtils.isNotBlank(content.getTitleDesc())) {
            criteria.andTitleDescLike("%" + content.getTitleDesc() + "%");
        }
        int total = (int) contentMapper.countByExample(example);
        example.setLimitStart(start);
        example.setLimitEnd(length);
        List<Content> contentList = contentMapper.selectByExample(example);
        pageInfo.setDraw(draw)
                .setRecordsTotal(total)
                .setRecordsFiltered(total)
                .setData(contentList)
                .setError("");
        return pageInfo;
    }

    @Override
    public Content selectOne(Integer id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    @Override
    public BaseResult save(Content content) {
        String validator = BeanValidator.validator(content);
        //验证不通过
        if (validator != null) {
            return BaseResult.not_ok(validator);
        }
        //新增用户
        if (content.getId() == null) {
            contentMapper.insertSelective(content);
        } else {
            contentMapper.updateByPrimaryKeySelective(content);
        }
        return BaseResult.ok("保存内容信息成功");
    }
}
