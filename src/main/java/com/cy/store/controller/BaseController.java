package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    /**
     * 请求处理的方法
     *
     * @return 需要返回给前端的数据
     */
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)//拦截ServiceException类型的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已存在！");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("在用户数据插入时发生了错误！");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户不存在！");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密码错误！");
        } else if (e instanceof  UpdateException) {
            result.setState(4003);
            result.setMessage("数据修改时发生错误，修改失败！");
        }
        return result;
    }

    protected Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
