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
    public int add(T t) {
        return mapper.insertSelective(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMul(E e) {
        return mapper.deleteByExample(e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExample(T t, E e) {
        return mapper.updateByExampleSelective(t, e);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult save(T t, String successMsg) {
        int result = 0;
        String validator = BeanValidator.validator(t);
        if (validator != null) {
            return BaseResult.not_ok(validator);
        }
        //新增
        if (t.getId() == null) {
            result = add(t);
        } else {
            result = update(t);
        }
        return result > 0 ?BaseResult.ok(successMsg):BaseResult.not_ok();
    }
}
