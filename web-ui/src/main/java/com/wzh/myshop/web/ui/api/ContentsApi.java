package com.wzh.myshop.web.ui.api;

import com.wzh.myshop.commons.utils.HttpClientUtils;
import com.wzh.myshop.commons.utils.JsonUtils;
import com.wzh.myshop.domain.entity.Content;

import java.util.List;

/**
 * @author wzh
 * @date 2019/10/10 - 10:05
 */
public class ContentsApi {
    /**
     * 请求幻灯片
     *
     * @return
     */
    public static List<Content> ppt() {
        List<Content> contents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
        try {
            contents = JsonUtils.json2listByTree(result, "data", Content.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
