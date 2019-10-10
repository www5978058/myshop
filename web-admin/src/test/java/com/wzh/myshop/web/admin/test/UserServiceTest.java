package com.wzh.myshop.web.admin.test;

import com.wzh.myshop.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzh
 * @date 2019/9/22 - 11:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void test(){
        userMapper.selectByExample(null).forEach(a->{
            System.out.println(a);
        });
    }
}
