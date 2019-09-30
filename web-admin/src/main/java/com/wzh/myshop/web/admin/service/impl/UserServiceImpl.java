package com.wzh.myshop.web.admin.service.impl;

import com.wzh.myshop.commons.base.BaseServiceImpl;
import com.wzh.myshop.commons.utils.MyEAESUtil;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.domain.entity.UserExample;
import com.wzh.myshop.web.admin.mapper.UserMapper;
import com.wzh.myshop.web.admin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author wzh
 * @date 2019/9/22 - 15:05
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserExample, UserMapper> implements UserService {


    @Override
    public User Login(String email, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> list = selectByExample(userExample);
        if (list.size() > 0) {
            User user = list.get(0);
            if (password.equals(MyEAESUtil.jiemi(user.getPassword()))) {
                return user;
            }
        }
        return null;
    }

}
