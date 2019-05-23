package com.artech.security.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 程江涛 on 2019/5/7.
 */

public class HttpUtils {

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        return tempContextUrl;
    }
}
