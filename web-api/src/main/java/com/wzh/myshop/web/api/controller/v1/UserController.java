package com.wzh.myshop.web.api.controller.v1;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.domain.entity.User;
import com.wzh.myshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzh
 * @date 2019/10/9 - 11:29
 */
@RestController
@RequestMapping("${api.path.v1}/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public BaseResult login(String email, String password) throws Exception {
        User user = userService.Login(email, password);
        if (user == null) {
            return BaseResult.not_ok("账号或密码错误");
        }
        return BaseResult.okWithData(user);
    }
}
