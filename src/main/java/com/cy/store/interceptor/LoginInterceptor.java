package com.cy.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    //自定义拦截器过滤规则
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看当前session中是否有用户登录后的信息
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null){
            //拦截,重定向到登录页面
            response.sendRedirect("/web/login.html");
            return false;
        }
        //放行
        return true;
    }
}
