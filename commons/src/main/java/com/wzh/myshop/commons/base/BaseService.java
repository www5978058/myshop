package com.wzh.myshop.commons.base;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;

import java.util.List;

/**
 *
 * @author wzh
 * @date 2019/9/28 - 13:06
 */
public interface BaseService<T extends BaseEntity,E extends BaseExample> {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T selectOne(Integer id);

    /**
     * 查询对象
     * @param e 条件
     * @return
     */
    List<T> selectByExample(E e);

    /**
     * 分页查询
     * @@param draw  页码
     * @param start 开始位置
     * @param length 查询数量
     * @param t 返回对象
     * @param e 条件
     * @return
     */
    PageInfo<T> page(int draw, int start, int length,T t, E e);

    /**
     * 查询行数
     * @param e
     * @return
     */
    int count(E e);

    /**
     * 添加对象
     * @param t
     */
    void add(T t);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param e
     */
    void deleteMul(E e);

    /**
     * 根据主键修改
     * @param t
     */
    void update(T t);

    /**
     * 条件修改对象
     * @param t
     * @param e
     */
    void updateByExample(T t,E e);

    /**
     * 保存对象
     * 有id修改，无id新增
     * @param t
     * @return 返回结果
     */
    BaseResult save(T t,String successMsg);
}
