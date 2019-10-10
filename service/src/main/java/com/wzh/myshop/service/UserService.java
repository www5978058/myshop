package com.wzh.myshop.service;

import com.wzh.myshop.commons.base.BaseService;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.domain.entity.UserExample;

/**
 * @author wzh
 * @date 2019/9/22 - 15:05
 */
public interface UserService extends BaseService<User, UserExample> {
    /**
     * 登录
     * @param email 邮箱
     * @param password 密码
     * @return 成功返回对象 失败返回null
     */
    User Login(String email, String password);

}
