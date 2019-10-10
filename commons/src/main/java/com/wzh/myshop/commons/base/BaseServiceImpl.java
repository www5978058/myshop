package com.wzh.myshop.commons.base;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import com.wzh.myshop.commons.validator.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/28 - 15:46
 */
public abstract class BaseServiceImpl<T extends BaseEntity, E extends BaseExample, M extends BaseMapper<T, E>> implements BaseService<T, E> {
    @Autowired
    M mapper;

    @Override
    public T selectOne(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectByExample(E e) {
        return mapper.selectByExample(e);
    }

    @Override
    public PageInfo<T> page(int draw, int start, int length, T t, E e) {
        PageInfo<T> pageInfo = new PageInfo<>();
        int total = count(e);
        e.setLimitStart(start);
        e.setLimitEnd(length);
        pageInfo.setDraw(draw)
                .setRecordsTotal(total)
                .setRecordsFiltered(total)
                .setData(mapper.selectByExample(e))
                .setError("");
        return pageInfo;
    }

    @Override
    public int count(E e) {
        return (int) mapper.countByExample(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(T t) {
        mapper.insertSelective(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMul(E e) {
        mapper.deleteByExample(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T t) {
        mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByExample(T t, E e) {
        mapper.updateByExampleSelective(t, e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult save(T t, String successMsg) {
        String validator = BeanValidator.validator(t);
        if (validator != null) {
            return BaseResult.not_ok(validator);
        }
        //新增
        if (t.getId() == null) {
            add(t);
        } else {
            update(t);
        }
        return BaseResult.ok(successMsg);
    }
}
