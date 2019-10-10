package com.wzh.myshop.web.ui.api;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.utils.HttpClientUtils;
import com.wzh.myshop.commons.utils.JsonUtils;
import com.wzh.myshop.domain.entity.User;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2019/10/9 - 13:57
 */
public class UserApi {
    /**
     * 登录
     * @return
     */
    public static User login(String email, String password) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN, new BasicNameValuePair[]{
                new BasicNameValuePair("email", email),
                new BasicNameValuePair("password", password)});
        User user = JsonUtils.json2pojoByTree(json,"data",User.class);
        return user;
    }
}
