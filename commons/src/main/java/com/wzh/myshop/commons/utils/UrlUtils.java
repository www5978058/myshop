package com.wzh.myshop.commons.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wzh
 * @date 2019/9/28 - 11:19
 */
public class UrlUtils {
    public static String getBaseUrl(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        return url.substring(0,url.length()-uri.length());
    }
}
