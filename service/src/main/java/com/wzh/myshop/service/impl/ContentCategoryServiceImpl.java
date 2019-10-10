package com.wzh.myshop.service.impl;

import com.wzh.myshop.commons.base.BaseServiceImpl;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.validator.BeanValidator;
import com.wzh.myshop.domain.entity.ContentCategory;
import com.wzh.myshop.domain.entity.ContentCategoryExample;
import com.wzh.myshop.domain.entity.ContentExample;
import com.wzh.myshop.mapper.ContentCategoryMapper;
import com.wzh.myshop.service.ContentCategoryService;
import com.wzh.myshop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2019/9/26 - 10:00
 */
@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory,ContentCategoryExample, ContentCategoryMapper> implements ContentCategoryService {
    @Autowired
    ContentService contentService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult save(ContentCategory contentCategory, String successMsg) {
        String validator = BeanValidator.validator(contentCategory);
        if (validator != null) {
            return BaseResult.not_ok(validator);
        }
        if (contentCategory.getId() == null) {
            //新增
            Integer parentId = contentCategory.getParentId();
            // 有父类节点 把父类节点的isParent设置为1
            if(parentId != 0){
                ContentCategory parentContentCategory = selectOne(parentId);
                update(parentContentCategory.setIsParent(1));
            }
            add(contentCategory);
        } else {
            update(contentCategory);
        }
        return BaseResult.ok(successMsg);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        List<Integer> target = new ArrayList<>();
        findAllChild(target,id);
        // 删除类目及其子类目
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andIdIn(target);
        super.deleteMul(example);
        // 删除类目下所有内容
        ContentExample e = new ContentExample();
        e.createCriteria().andCategoryIdIn(target);
        contentService.deleteMul(e);
    }

    /**
     * 查找出所有子节点
     *
     * @param targetList
     * @param parentId
     */
    private void findAllChild(List<Integer> targetList, Integer parentId) {
        targetList.add(parentId);
        ContentCategoryExample example = new ContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<ContentCategory> contentCategories = selectByExample(example);
        for (ContentCategory contentCategory : contentCategories) {
            findAllChild(targetList, contentCategory.getId());
        }
    }
}
