package com.zzw.animalserve.utils;


import javax.servlet.http.HttpServletResponse;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/8/27__15:00
 */
public class WebUtils {

    /**
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string){
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
