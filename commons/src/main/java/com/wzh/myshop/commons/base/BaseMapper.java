package com.wzh.myshop.commons.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/28 - 12:55
 */
public interface BaseMapper<T extends BaseEntity,E extends BaseExample> {
    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
