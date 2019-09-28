package com.wzh.myshop.web.admin.service;

import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;

/**
 * @author wzh
 * @date 2019/9/22 - 15:05
 */
public interface UserService {
    /**
     * 登录
     * @param email 邮箱
     * @param password 密码
     * @return 成功返回对象 失败返回null
     */
    User Login(String email, String password);

    /**
     * 保存用户信息
     * @param user
     */
    BaseResult save(User user);

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    User selectOne(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMul(Integer[] ids);

    /**
     * 分页查询
     * @param draw  页码
     * @param start 开始位置
     * @param length 查询数量
     * @param user 条件查询
     * @return
     */
    PageInfo<User> page(int draw, int start, int length,User user);
}
