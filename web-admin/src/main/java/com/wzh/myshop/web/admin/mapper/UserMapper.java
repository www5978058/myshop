package com.wzh.myshop.web.admin.mapper;

import com.wzh.myshop.commons.base.BaseMapper;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.domain.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User,UserExample> {
}
