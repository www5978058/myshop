package com.wzh.myshop.web.admin.service.impl;

import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.domain.entity.UserExample;
import com.wzh.myshop.web.admin.mapper.UserMapper;
import com.wzh.myshop.web.admin.service.UserService;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wzh.myshop.commons.utils.MyEAESUtil;
import com.wzh.myshop.commons.validator.BeanValidator;

import java.util.Arrays;
import java.util.List;

/**
 * @author wzh
 * @date 2019/9/22 - 15:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User Login(String email, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() > 0) {
            User user = list.get(0);
            if (password.equals(MyEAESUtil.jiemi(user.getPassword()))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(User user) {
        String validator = BeanValidator.validator(user);
        //验证不通过
        if (validator != null) {
            return BaseResult.not_ok(validator);
        }
        user.setPassword(StringUtils.isNotBlank(user.getPassword())?MyEAESUtil.jiami(user.getPassword()):null);
        //新增用户
        if (user.getId() == null) {
            userMapper.insertSelective(user);
        } else {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return BaseResult.ok("保存用户信息成功");
    }

    @Override
    public User selectOne(Integer id) {
        return userMapper.selectByPrimaryKey(id).setPassword(null);
    }

    @Override
    public void deleteMul(Integer[] ids) {
        UserExample example = new UserExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        userMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<User> page(int draw, int start, int length, User user) {
        PageInfo<User> pageInfo = new PageInfo<>();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUsername())) {
            criteria.andUsernameLike("%" + user.getUsername() + "%");
        }
        if (StringUtils.isNotBlank(user.getMobileNumber())) {
            criteria.andMobileNumberLike("%" + user.getMobileNumber() + "%");
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            criteria.andEmailLike("%" + user.getEmail() + "%");
        }
        int total = (int) userMapper.countByExample(example);
        example.setLimitStart(start);
        example.setLimitEnd(length);
        List<User> userList = userMapper.selectByExample(example);
        pageInfo.setDraw(draw)
                .setRecordsTotal(total)
                .setRecordsFiltered(total)
                .setData(userList)
                .setError("");
        return pageInfo;
    }


}
